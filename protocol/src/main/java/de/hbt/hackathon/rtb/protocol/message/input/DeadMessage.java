package de.hbt.hackathon.rtb.protocol.message.input;

public class DeadMessage extends InputMessage {

	private static final DeadMessage SINGLETON = new DeadMessage();

	public static DeadMessage valueOf(String[] args) {
		return SINGLETON;
	}
	
}
