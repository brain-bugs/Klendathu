package de.hbt.hackathon.rtb.protocol.message.input;

public class YourColourMessage {

	private final String colour;

	private YourColourMessage(String colour) {
		this.colour = colour;
	}

	public String getColour() {
		return colour;
	}

	public YourColourMessage valueOf(String[] args) {
		return new YourColourMessage(args[1]);
	}

}
