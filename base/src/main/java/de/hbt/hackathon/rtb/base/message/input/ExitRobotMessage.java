package de.hbt.hackathon.rtb.base.message.input;

public class ExitRobotMessage extends InputMessage {
	
	private static final ExitRobotMessage SINGLETON = new ExitRobotMessage();

	public static ExitRobotMessage valueOf(String[] args) {
		return SINGLETON;
	}

}
