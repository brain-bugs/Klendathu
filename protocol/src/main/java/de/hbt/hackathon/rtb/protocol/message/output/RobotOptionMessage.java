package de.hbt.hackathon.rtb.protocol.message.output;

import de.hbt.hackathon.rtb.protocol.type.RobotOptionType;

public class RobotOptionMessage extends OutputMessage {

	private final RobotOptionType robotOptionType;
	private final int value;

	public RobotOptionMessage(RobotOptionType robotOptionType, int value) {
		this.robotOptionType = robotOptionType;
		this.value = value;
	}

	public RobotOptionType getRobotOptionType() {
		return robotOptionType;
	}

	public int getValue() {
		return value;
	}

}
