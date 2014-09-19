package de.hbt.hackathon.rtb.world;

import de.hbt.hackathon.rtb.base.type.Coordinate;

public class Robot extends FixedGameObject {

	private final boolean teamMate;

	private double energyLevel;

	private double speed;

	private double cannonAngle;

	public Robot(Coordinate coordinate, boolean teamMate) {
		super(coordinate);
		this.teamMate = teamMate;
	}

	public boolean isTeamMate() {
		return teamMate;
	}

	public double getEnergyLevel() {
		return energyLevel;
	}

	public void setEnergyLevel(double energyLevel) {
		this.energyLevel = energyLevel;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getCannonAngle() {
		return cannonAngle;
	}

	public void setCannonAngle(double cannonAngle) {
		this.cannonAngle = cannonAngle;
	}

}
