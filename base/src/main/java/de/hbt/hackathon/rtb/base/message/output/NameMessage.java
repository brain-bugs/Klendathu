package de.hbt.hackathon.rtb.base.message.output;

public class NameMessage extends OutputMessage {

	private final String name;

	public NameMessage(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
