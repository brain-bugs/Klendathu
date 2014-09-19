package de.hbt.hackathon.rtb.strategy;

import de.hbt.hackathon.rtb.base.type.Coordinate;

public class Targeter {

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

}
