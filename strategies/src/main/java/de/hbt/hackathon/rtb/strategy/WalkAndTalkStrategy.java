package de.hbt.hackathon.rtb.strategy;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.operation.buffer.BufferParameters;

import de.hbt.hackathon.rtb.base.geo.JTSQuadTreeAdapter;
import de.hbt.hackathon.rtb.base.message.AngleType;
import de.hbt.hackathon.rtb.base.message.output.AccelerateMessage;
import de.hbt.hackathon.rtb.base.message.output.BrakeMessage;
import de.hbt.hackathon.rtb.base.message.output.OutputMessage;
import de.hbt.hackathon.rtb.base.message.output.RotateAmountMessage;
import de.hbt.hackathon.rtb.base.message.output.RotateMessage;
import de.hbt.hackathon.rtb.base.strategy.AbstractStrategy;
import de.hbt.hackathon.rtb.world.MyRobot;
import de.hbt.hackathon.rtb.world.Robot;
import de.hbt.hackathon.rtb.world.World;

public class WalkAndTalkStrategy extends AbstractStrategy {

	private static final Logger LOG = LoggerFactory.getLogger(WalkAndTalkStrategy.class);

	private final String NAME = "WalkAndTalk";

	private final World world;

	private static final GeometryFactory GF = new GeometryFactory();

	private double turnDirection = 1.0;

	private int counter = 0;

	private final Random random = new Random();

	private Robot targetRobot;

	Targeter targeter = new Targeter();

	public WalkAndTalkStrategy(World world) {
		this.world = world;
	}

	@Override
	public String getName() {
		return NAME;
	}

	@Override
	public List<OutputMessage> process() {
		MyRobot myself = world.getMyRobot();
		if (myself == null || myself.getCurrentPosition() == null)
			return Collections.emptyList();

		double rotationAngle = myself.getRotationAngle();
		de.hbt.hackathon.rtb.base.type.Coordinate pos = myself.getCurrentPosition();
		Coordinate from = new Coordinate(pos.getX(), pos.getY());
		double distance = myself.getSpeed() * 1.5;
		double dx = distance * Math.cos(rotationAngle);
		double dy = distance * Math.sin(rotationAngle);
		Coordinate to = new Coordinate(pos.getX() + dx, pos.getY() + dy);
		LineString centerLine = GF.createLineString(new Coordinate[] { from, to });
		Geometry buffer = centerLine.buffer(0.5, 1, BufferParameters.CAP_FLAT);
		List<OutputMessage> messages = new LinkedList<OutputMessage>();

		JTSQuadTreeAdapter<Robot> robots = world.getRobots();
		if (!robots.isEmpty()) {
			targetRobot = (Robot) robots.getIntersecting(buffer);
			Iterator<Robot> iterator = robots.iterator();
			while ((targetRobot == null || targetRobot.isTeamMate()) && iterator.hasNext()) {
				targetRobot = iterator.next();
			}
			if (targetRobot.isTeamMate())
				targetRobot = null;
		} else {
			targetRobot = null;
		}

		if (world.getMines().intersectsGeometry(buffer) || world.getWalls().intersectsGeometry(buffer)) {
			LOG.info("Achtung Mauer!");
			double rotateAmount = turnDirection * 2.5 * random.nextDouble();
			messages.add(new RotateAmountMessage(EnumSet.of(AngleType.ROBOT), getCapabilities().getMaxRotate(), rotateAmount));
			counter++;
			if (myself.getSpeed() >= 2.5) {
				messages.add(new AccelerateMessage(-1.0));
				messages.add(new BrakeMessage(1.0));
			}
			if (counter > 100) {
				counter = 0;
				turnDirection *= -1;
			}
		} else if (targetRobot != null) {
			RotateMessage rm = new RotateMessage(EnumSet.of(AngleType.RADAR, AngleType.CANNON), 0.0);
			messages.add(rm);
			de.hbt.hackathon.rtb.base.type.Coordinate targetPos = targetRobot.getCurrentPosition();
			messages.addAll(targeter.aimCannonToAndShoot(myself, targetPos, getCapabilities().getMaxCannonRotate(), getCapabilities()
					.getMaxShotEnergy() / 3d));
			targeter.setOwnLocation(myself.getCurrentPosition(), AngleUtils.normalizeAngle(myself.getRotationAngle()));
			double targetAngle = AngleUtils.normalizeAngle(targeter.calculateAngleTowards(targetRobot.getCurrentPosition()));
			messages.add(new RotateAmountMessage(EnumSet.of(AngleType.ROBOT), getCapabilities().getMaxRotate(), targetAngle));
			messages.add(new BrakeMessage(0.0));
			messages.add(new AccelerateMessage(0.5));
		} else {
			RotateMessage rm = new RotateMessage(EnumSet.of(AngleType.RADAR, AngleType.CANNON), 0.5);
			messages.add(rm);
			messages.add(new BrakeMessage(0.0));
			messages.add(new AccelerateMessage(1.0));
		}

		return messages;
	}
}
