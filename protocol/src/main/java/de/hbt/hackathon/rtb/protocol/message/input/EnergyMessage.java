package de.hbt.hackathon.rtb.protocol.message.input;

public class EnergyMessage {
	
	private final int energyLevel;

	private EnergyMessage(int energyLevel) {
		this.energyLevel = energyLevel;
	}
	
	public int getEnergyLevel() {
		return energyLevel;
	}
	
	public EnergyMessage valueOf(String[] args) {
		int energyLevel = Integer.parseInt(args[1]);
		return new EnergyMessage(energyLevel);
	}
}
