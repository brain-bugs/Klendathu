package de.hbt.hackathon.rtb.base.message.output;

public class PrintMessage extends OutputMessage {

	private final String message;

	public PrintMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
