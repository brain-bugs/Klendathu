package de.hbt.hackathon.rtb.protocol.message.output;

public class BrakeMessage {
	
	private static final BrakeMessage FULL_BRAKE_MESSAGE = new BrakeMessage(1.0);
	
	private final double portion;
	
	public BrakeMessage(double portion) {
		this.portion = portion;
	}

	public static BrakeMessage fullBrake() {
		return FULL_BRAKE_MESSAGE;
	}

}
