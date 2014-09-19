package de.hbt.hackathon.rtb.world;

import de.hbt.hackathon.rtb.base.Coordinate;

public class MyRobot extends Robot {

	private double acceleration;
	private double breakFraction;
	private double rotationAngle;
	private double radarAngle;
	private double cannonSweepAngularVelocity;
	private double cannonSweepRightAngle;
	private double cannonSweepLeftAngle;
	private double radarSweepAngularVelocity;
	private double radarSweepRightAngle;
	private double radarSweepLeftAngle;

	public MyRobot(Coordinate coordinate) {
		super(coordinate, true);
	}

	public double getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(double acceleration) {
		this.acceleration = acceleration;
	}

	public double getBreakFraction() {
		return breakFraction;
	}

	public void setBreakFraction(double breakFraction) {
		this.breakFraction = breakFraction;
	}

	public double getRotationAngle() {
		return rotationAngle;
	}

	public void setRotationAngle(double rotationAngle) {
		this.rotationAngle = rotationAngle;
	}

	public double getRadarAngle() {
		return radarAngle;
	}

	public void setRadarAngle(double radarAngle) {
		this.radarAngle = radarAngle;
	}

	public double getCannonSweepAngularVelocity() {
		return cannonSweepAngularVelocity;
	}

	public void setCannonSweepAngularVelocity(double cannonSweepAngularVelocity) {
		this.cannonSweepAngularVelocity = cannonSweepAngularVelocity;
	}

	public double getCannonSweepRightAngle() {
		return cannonSweepRightAngle;
	}

	public void setCannonSweepRightAngle(double cannonSweepRightAngle) {
		this.cannonSweepRightAngle = cannonSweepRightAngle;
	}

	public double getCannonSweepLeftAngle() {
		return cannonSweepLeftAngle;
	}

	public void setCannonSweepLeftAngle(double cannonSweepLeftAngle) {
		this.cannonSweepLeftAngle = cannonSweepLeftAngle;
	}

	public double getRadarSweepAngularVelocity() {
		return radarSweepAngularVelocity;
	}

	public void setRadarSweepAngularVelocity(double radarSweepAngularVelocity) {
		this.radarSweepAngularVelocity = radarSweepAngularVelocity;
	}

	public double getRadarSweepRightAngle() {
		return radarSweepRightAngle;
	}

	public void setRadarSweepRightAngle(double radarSweepRightAngle) {
		this.radarSweepRightAngle = radarSweepRightAngle;
	}

	public double getRadarSweepLeftAngle() {
		return radarSweepLeftAngle;
	}

	public void setRadarSweepLeftAngle(double radarSweepLeftAngle) {
		this.radarSweepLeftAngle = radarSweepLeftAngle;
	}

}
