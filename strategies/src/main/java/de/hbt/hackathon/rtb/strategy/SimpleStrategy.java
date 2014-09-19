package de.hbt.hackathon.rtb.strategy;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import de.hbt.hackathon.rtb.base.message.AngleType;
import de.hbt.hackathon.rtb.base.message.output.AccelerateMessage;
import de.hbt.hackathon.rtb.base.message.output.BrakeMessage;
import de.hbt.hackathon.rtb.base.message.output.OutputMessage;
import de.hbt.hackathon.rtb.base.message.output.RotateAmountMessage;
import de.hbt.hackathon.rtb.base.message.output.RotateMessage;
import de.hbt.hackathon.rtb.base.strategy.AbstractStrategy;
import de.hbt.hackathon.rtb.world.MyRobot;
import de.hbt.hackathon.rtb.world.World;

public class SimpleStrategy extends AbstractStrategy {

	private final String NAME = "Simple";

	private final World world;

	private int counter = 0;

	public SimpleStrategy(World world) {
		this.world = world;
	}

	@Override
	public List<OutputMessage> process() {

		List<OutputMessage> messages = new ArrayList<OutputMessage>();

		RotateAmountMessage rm = new RotateAmountMessage(EnumSet.of(AngleType.ROBOT), 1.0, 1.0);
		messages.add(rm);

		// MyRobot myRobot = world.getMyRobot();
		// double speed = myRobot.getSpeed();
		// myRobot.getRotationAngle();
		//
		// GeometricSet<Wall> walls = world.getWalls();

		if (counter > 100) {
			AccelerateMessage am = new AccelerateMessage(0.0);
			messages.add(am);
			MyRobot myRobot = world.getMyRobot();
			double speed = myRobot.getSpeed();
			if (speed > 0.001) {
				BrakeMessage bm = new BrakeMessage(1.0);
				messages.add(bm);
			} else {
				counter = -50;
			}
		} else if (counter >= 0) {
			BrakeMessage bm = new BrakeMessage(0.0);
			messages.add(bm);
			AccelerateMessage am = new AccelerateMessage(1.0);
			messages.add(am);
			counter++;
		} else {
			counter++;
		}

		RotateMessage rm2 = new RotateMessage(EnumSet.of(AngleType.RADAR), 1.0);
		messages.add(rm2);

		return messages;
	}

	@Override
	public String getName() {
		return NAME;
	}

}
