package de.hbt.hackathon.rtb.base.message.input;

public class RobotInfoMessage extends InputMessage {

	private final double energyLevel;
	private final boolean teamMate;

	private RobotInfoMessage(double energyLevel, boolean teamMate) {
		this.energyLevel = energyLevel;
		this.teamMate = teamMate;
	}

	public double getEnergyLevel() {
		return energyLevel;
	}

	public boolean isTeamMate() {
		return teamMate;
	}

	public static RobotInfoMessage valueOf(String[] args) {
		double energyLevel = Double.parseDouble(args[1]);
		int teamMateValue = Integer.parseInt(args[2]);
		return new RobotInfoMessage(energyLevel, teamMateValue == 1);
	}
}
