package de.hbt.hackathon.rtb.base.message.input;

import de.hbt.hackathon.rtb.base.type.Coordinate;


public class CoordinatesMessage extends InputMessage {

	private final Coordinate coordinate;
	private final double robotAngle;

	private CoordinatesMessage(Coordinate coordinate, double robotAngle) {
		this.coordinate = coordinate;
		this.robotAngle = robotAngle;
	}

	public Coordinate getCoordinate() {
		return coordinate;
	}

	public double getRobotAngle() {
		return robotAngle;
	}

	public static CoordinatesMessage valueOf(String[] args) {
		double x = Double.parseDouble(args[1]);
		double y = Double.parseDouble(args[2]);
		double robotAngle = Double.parseDouble(args[3]);
		return new CoordinatesMessage(new Coordinate(x, y, System.currentTimeMillis()), robotAngle);
	}
}
