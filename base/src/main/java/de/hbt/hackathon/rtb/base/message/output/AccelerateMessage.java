package de.hbt.hackathon.rtb.base.message.output;

public class AccelerateMessage extends OutputMessage {

	private final double value;

	public AccelerateMessage(double value) {
		super();
		this.value = value;
	}

	public double getValue() {
		return value;
	}

}
