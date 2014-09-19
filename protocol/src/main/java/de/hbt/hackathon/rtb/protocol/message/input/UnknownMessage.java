package de.hbt.hackathon.rtb.protocol.message.input;

public class UnknownMessage extends InputMessage {
	
	private final String inputValue;
	
	private UnknownMessage(String inputValue) {
		this.inputValue = inputValue;
	}

	public static UnknownMessage valueOf(String inputValue) {
		return new UnknownMessage(inputValue);
	}

}
