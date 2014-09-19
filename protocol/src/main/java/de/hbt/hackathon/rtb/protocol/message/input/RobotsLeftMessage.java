package de.hbt.hackathon.rtb.protocol.message.input;

public class RobotsLeftMessage extends InputMessage {

	private final int numberOfRobots;

	private RobotsLeftMessage(int numberOfRobots) {
		this.numberOfRobots = numberOfRobots;
	}

	public int getNumberOfRobots() {
		return numberOfRobots;
	}

	public static RobotsLeftMessage valueOf(String[] args) {
		int numberOfRobots = Integer.parseInt(args[1]);
		return new RobotsLeftMessage(numberOfRobots);
	}

}
