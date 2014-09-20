package de.hbt.hackathon.rtb.strategy;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.hbt.hackathon.rtb.base.geo.GeometricSet;
import de.hbt.hackathon.rtb.base.message.AngleType;
import de.hbt.hackathon.rtb.base.message.output.AccelerateMessage;
import de.hbt.hackathon.rtb.base.message.output.BrakeMessage;
import de.hbt.hackathon.rtb.base.message.output.OutputMessage;
import de.hbt.hackathon.rtb.base.message.output.RotateAmountMessage;
import de.hbt.hackathon.rtb.base.message.output.RotateMessage;
import de.hbt.hackathon.rtb.base.strategy.AbstractStrategy;
import de.hbt.hackathon.rtb.base.type.Coordinate;
import de.hbt.hackathon.rtb.world.Cookie;
import de.hbt.hackathon.rtb.world.GameObject;
import de.hbt.hackathon.rtb.world.Mine;
import de.hbt.hackathon.rtb.world.MyRobot;
import de.hbt.hackathon.rtb.world.Robot;
import de.hbt.hackathon.rtb.world.World;

public class HealingStrategy extends AbstractStrategy {

	private static final Logger LOGGER = LoggerFactory.getLogger(SimpleStrategy.class);

	private final String NAME = "Healing";

	private final World world;

	public HealingStrategy(World world) {
		this.world = world;
	}

	@Override
	public List<OutputMessage> process() {
		List<OutputMessage> messages = new ArrayList<OutputMessage>();
		messages.add(new RotateMessage(EnumSet.of(AngleType.RADAR), getCapabilities().getMaxRadarRotate()));

		MyRobot myRobot = world.getMyRobot();
		Set<Cookie> cookies = world.getCookies();
		GeometricSet<Mine> mines = world.getMines();
		GeometricSet<Robot> robots = world.getRobots();
		if (!cookies.isEmpty()) {
			LOGGER.info("There are cookies!");
			if (myRobot != null) {
				Cookie cookie = cookies.iterator().next();
				Targeter targeter = new Targeter();
				messages.addAll(targeter.driveTo(myRobot, cookie.getCurrentPosition()));
			}
		}
		Mine mineToAttack = null;
		Robot robotToAttack = null;
		if (!mines.isEmpty()) {
			LOGGER.info("There are mines!");
			if (myRobot != null) {
				mineToAttack = getClosestGameObject(mines.iterator(), myRobot.getCurrentPosition());
				if (mineToAttack.getCurrentPosition().distance(myRobot.getCurrentPosition()) > 4) {
					mineToAttack = null;
				}
			}
		}
		if (!robots.isEmpty()) {
			if (myRobot != null) {
				robotToAttack = getNewestGameObject(robots.iterator());
			}
		}
		if (robotToAttack != null) {
			Targeter targeter = new Targeter();
			messages.addAll(targeter.aimCannonToAndShoot(myRobot, robotToAttack.getCurrentPosition(), getCapabilities()
					.getMaxCannonRotate(), getCapabilities().getMaxShotEnergy() / 3d));
		} else if (mineToAttack != null) {
			Targeter targeter = new Targeter();
			messages.addAll(targeter.aimCannonToAndShoot(myRobot, mineToAttack.getCurrentPosition(),
					getCapabilities().getMaxCannonRotate(), getCapabilities().getMinShotEnergy()));
		} else {
			RotateAmountMessage rm = new RotateAmountMessage(EnumSet.of(AngleType.ROBOT), 1.0, 1.0);
			messages.add(rm);
			AccelerateMessage am = new AccelerateMessage(1.0);
			messages.add(am);
			BrakeMessage bm = new BrakeMessage(0.0);
			messages.add(bm);
		}
		return messages;
	}

	private static <E extends GameObject> E getNewestGameObject(Iterator<E> iterator) {
		double newestTimeStamp = 0;
		E newestGameObject = null;
		while (iterator.hasNext()) {
			E gameObject = iterator.next();
			if (gameObject.getCurrentPosition().getTimeStamp() > newestTimeStamp) {
				newestGameObject = gameObject;
				newestTimeStamp = gameObject.getCurrentPosition().getTimeStamp();
			}
		}
		return newestGameObject;
	}

	private static <E extends GameObject> E getClosestGameObject(Iterator<E> iterator, Coordinate position) {
		double closestDistance = Double.MAX_VALUE;
		E closestGameObject = null;
		while (iterator.hasNext()) {
			E gameObject = iterator.next();
			double distance = gameObject.getCurrentPosition().distance(position);
			if (distance < closestDistance) {
				closestGameObject = gameObject;
				closestDistance = distance;
			}
		}
		return closestGameObject;
	}

	@Override
	public String getName() {
		return NAME;
	}

}
