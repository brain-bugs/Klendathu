package de.hbt.hackathon.rtb.base.type;

public class Coordinate {

	private final double x;
	private final double y;

	private final double timeStamp;

	public Coordinate(double x, double y, double timeStamp) {
		this.x = x;
		this.y = y;
		this.timeStamp = timeStamp;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getTimeStamp() {
		return timeStamp;
	}

	public double distance(Coordinate other) {
		return Math.sqrt((x - other.x) * (x - other.x) + (y - other.y) * (y - other.y));
	}

	@Override
	public String toString() {
		return "Coordinate[x=" + x + ",y=" + y + ",timeStamp=" + timeStamp;
	}

}
