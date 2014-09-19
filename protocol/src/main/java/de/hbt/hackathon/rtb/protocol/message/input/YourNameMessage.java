package de.hbt.hackathon.rtb.protocol.message.input;

public class YourNameMessage extends InputMessage {
	
	private final String name;

	private YourNameMessage(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public static YourNameMessage valueOf(String[] args) {
		return new YourNameMessage(args[1]);
	}
	
}
