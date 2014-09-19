package de.hbt.hackathon.rtb.world;

import de.hbt.hackathon.rtb.base.Coordinate;

public class Robot extends MovingGameObject {

	private final boolean teamMate;
	private double energyLevel;
	
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

}
