package de.hbt.hackathon.rtb.base.message.input;

public class InitializeMessage extends InputMessage {
	
	private final boolean first;
	
	private InitializeMessage(boolean first) {
		this.first = first;
	}

	public boolean isFirst() {
		return first;
	}
	
	public static InitializeMessage valueOf(String[] args) {
		int firstValue = Integer.parseInt(args[1]);
		return new InitializeMessage(firstValue == 1);
	}

}
