package de.hbt.hackathon.rtb.protocol.message.input;

public class RobotsLeftMessage {

	private final int numberOfRobots;

	private RobotsLeftMessage(int numberOfRobots) {
		this.numberOfRobots = numberOfRobots;
	}

	public int getNumberOfRobots() {
		return numberOfRobots;
	}

	public RobotsLeftMessage valueOf(String[] args) {
		int numberOfRobots = Integer.parseInt(args[1]);
		return new RobotsLeftMessage(numberOfRobots);
	}

}
