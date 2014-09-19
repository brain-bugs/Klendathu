package de.hbt.hackathon.rtb.base.message.output;

public class ShootMessage extends OutputMessage {

	private final double energy;

	public ShootMessage(double energy) {
		this.energy = energy;
	}

	public double getEnergy() {
		return energy;
	}

}
