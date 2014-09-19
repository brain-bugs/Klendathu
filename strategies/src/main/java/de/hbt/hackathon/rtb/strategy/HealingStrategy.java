package de.hbt.hackathon.rtb.strategy;

import java.util.ArrayList;
import java.util.EnumSet;
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
import de.hbt.hackathon.rtb.base.strategy.AbstractStrategy;
import de.hbt.hackathon.rtb.world.Cookie;
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

		MyRobot myRobot = world.getMyRobot();
		Set<Cookie> cookies = world.getCookies();
		GeometricSet<Mine> mines = world.getMines();
		GeometricSet<Robot> robots = world.getRobots();
		if (!cookies.isEmpty()) {
			LOGGER.info("There are cookies!");
			if (myRobot != null) {
				Cookie cookie = cookies.iterator().next();
				Targeter targeter = new Targeter();
				targeter.driveTo(myRobot, cookie.getCurrentPosition());
			}
		} 
		
		if (!mines.isEmpty()) {
			LOGGER.info("There are mines!");
			if (myRobot != null) {
				Mine mine = mines.iterator().next();
				Targeter targeter = new Targeter();
				targeter.aimCannonToAndShoot(myRobot, mine.getCurrentPosition());
			}
		} else if (!robots.isEmpty()) {
			LOGGER.info("There are robots!");
			if (myRobot != null) {
				Robot robot = robots.iterator().next();
				Targeter targeter = new Targeter();
				targeter.aimCannonToAndShoot(myRobot, robot.getCurrentPosition());
			}
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

	@Override
	public String getName() {
		return NAME;
	}

}
