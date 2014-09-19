package de.hbt.hackathon.rtb.strategy;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.hbt.hackathon.rtb.base.message.AngleType;
import de.hbt.hackathon.rtb.base.message.output.AccelerateMessage;
import de.hbt.hackathon.rtb.base.message.output.BrakeMessage;
import de.hbt.hackathon.rtb.base.message.output.OutputMessage;
import de.hbt.hackathon.rtb.base.message.output.RotateAmountMessage;
import de.hbt.hackathon.rtb.base.message.output.ShootMessage;
import de.hbt.hackathon.rtb.base.type.Coordinate;
import de.hbt.hackathon.rtb.world.MyRobot;

public class Targeter {
	private static final Logger LOGGER = LoggerFactory.getLogger(Targeter.class);

	private Coordinate ownPosition;
	private double ownAngle;

	public void setOwnLocation(Coordinate ownPosition, double ownAngle) {
		this.ownPosition = ownPosition;
		this.ownAngle = ownAngle;
	}

	public double calculateAngleTowards(Coordinate target) {
		double targetAngle = AngleUtils.getAngle(ownPosition.getX(), ownPosition.getY(), target.getX(), target.getY());
		return targetAngle - ownAngle;
	}
	
	public List<OutputMessage> driveTo(MyRobot myRobot, Coordinate targetCoordinate) {
		List<OutputMessage> messages = new ArrayList<OutputMessage>();
		this.setOwnLocation(myRobot.getCurrentPosition(), AngleUtils.normalizeAngle(myRobot.getRotationAngle()));
		double rotationAngle = AngleUtils.normalizeAngle(this.calculateAngleTowards(targetCoordinate));
		double distance = targetCoordinate.distance(myRobot.getCurrentPosition());
		double angleOffset = (Math.toDegrees(rotationAngle) < 180d) ? 1d : -1d;

		LOGGER.info("distance to target: " + distance + ", angle: " + Math.toDegrees(rotationAngle));
		LOGGER.debug("My position: " + myRobot.getCurrentPosition() + ", angle: "
				+ Math.toRadians(AngleUtils.normalizeAngle(myRobot.getRotationAngle())) + ", speed: " + myRobot.getSpeed());
		LOGGER.info("Target position: " + targetCoordinate);
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
		
		return messages;
	}
	
	public List<OutputMessage> aimCannonToAndShoot(MyRobot myRobot, Coordinate targetCoordinate) {
		List<OutputMessage> messages = new ArrayList<OutputMessage>();
		this.setOwnLocation(myRobot.getCurrentPosition(), AngleUtils.normalizeAngle(myRobot.getCannonAngle()));
		double rotationAngle = AngleUtils.normalizeAngle(this.calculateAngleTowards(targetCoordinate));
		double angleOffset = (Math.toDegrees(rotationAngle) < 180d) ? 1d : -1d;

		LOGGER.info("angle to target: " + Math.toDegrees(rotationAngle));
		LOGGER.debug("My position: " + myRobot.getCurrentPosition() + ", cannon angle: "
				+ Math.toRadians(AngleUtils.normalizeAngle(myRobot.getCannonAngle())));
		LOGGER.info("Target position: " + targetCoordinate);
		
		if (rotationAngle > 175d && rotationAngle < 185d) {
			messages.add(new ShootMessage(1));
		}
			
		RotateAmountMessage rm = new RotateAmountMessage(EnumSet.of(AngleType.CANNON), 1.0, angleOffset);
		LOGGER.info("Rotate by " + angleOffset + " radians");
		messages.add(rm);
		
		return messages;
	}

}
