package de.hbt.hackathon.rtb.strategy;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.hbt.hackathon.rtb.base.message.AngleType;
import de.hbt.hackathon.rtb.base.message.output.AccelerateMessage;
import de.hbt.hackathon.rtb.base.message.output.BrakeMessage;
import de.hbt.hackathon.rtb.base.message.output.OutputMessage;
import de.hbt.hackathon.rtb.base.message.output.RotateAmountMessage;
import de.hbt.hackathon.rtb.base.strategy.AbstractStrategy;
import de.hbt.hackathon.rtb.world.Cookie;
import de.hbt.hackathon.rtb.world.MyRobot;
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

		Set<Cookie> cookies = world.getCookies();
		if (!cookies.isEmpty()) {
			LOGGER.info("There are cookies!");
			MyRobot myRobot = world.getMyRobot();
			if (myRobot != null) {
				Cookie cookie = cookies.iterator().next();
				Targeter targeter = new Targeter();
				targeter.setOwnLocation(myRobot.getCurrentPosition(), AngleUtils.normalizeAngle(myRobot.getRotationAngle()));
				double rotationAngle = AngleUtils.normalizeAngle(targeter.calculateAngleTowards(cookie.getCurrentPosition()));
				double distance = cookie.getCurrentPosition().distance(myRobot.getCurrentPosition());
				double angleOffset = (Math.toDegrees(rotationAngle) < 180d) ? 1d : -1d;

				LOGGER.info("distance to cookie: " + distance + ", angle: " + Math.toDegrees(rotationAngle));
				LOGGER.debug("My position: " + myRobot.getCurrentPosition() + ", angle: "
						+ Math.toRadians(AngleUtils.normalizeAngle(myRobot.getRotationAngle())) + ", speed: " + myRobot.getSpeed());
				LOGGER.info("Cookie position: " + cookie.getCurrentPosition());
				RotateAmountMessage rm = new RotateAmountMessage(EnumSet.of(AngleType.ROBOT), 1.0, angleOffset);
				LOGGER.info("Rotate by " + angleOffset + " radians");
				messages.add(rm);
				if ((myRobot.getSpeed() > 0.5) && (Math.toDegrees(rotationAngle) > 20) && (Math.toDegrees(rotationAngle) < 340)) {
					BrakeMessage bm = new BrakeMessage(1.0);
					LOGGER.info("Full Break");
					messages.add(bm);
				} else {
					AccelerateMessage am = new AccelerateMessage(1.0);
					LOGGER.info("Accelerate by 1.0");
					messages.add(am);
					BrakeMessage bm = new BrakeMessage(0.0);
					messages.add(bm);
				}
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
