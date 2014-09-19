package de.hbt.hackathon.rtb.protocol.message.output;

public class ColourMessage extends OutputMessage {

	private final String homeColour;
	private final String awayColour;

	public ColourMessage(String homeColour, String awayColour) {
		this.homeColour = homeColour;
		this.awayColour = awayColour;
	}

	public String getHomeColour() {
		return homeColour;
	}

	public String getAwayColour() {
		return awayColour;
	}

}
