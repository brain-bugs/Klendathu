package de.hbt.hackathon.rtb.base;

public class Coordinate {

	private final double x;
	private final double y;
	
	private final long timeStamp;

	public Coordinate(double x, double y, long timeStamp) {
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
	
	public long getTimeStamp() {
		return timeStamp;
	}

}
