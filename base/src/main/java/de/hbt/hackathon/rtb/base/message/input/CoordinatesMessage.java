package de.hbt.hackathon.rtb.base.message.input;


public class CoordinatesMessage extends InputMessage {

	private final double x, y;
	private final double robotAngle;

	private CoordinatesMessage(double x, double y, double robotAngle) {
		this.x = x;
		this.y = y;
		this.robotAngle = robotAngle;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getRobotAngle() {
		return robotAngle;
	}

	public static CoordinatesMessage valueOf(String[] args) {
		double x = Double.parseDouble(args[1]);
		double y = Double.parseDouble(args[2]);
		double robotAngle = Double.parseDouble(args[3]);
		return new CoordinatesMessage(x, y, robotAngle);
	}
}
