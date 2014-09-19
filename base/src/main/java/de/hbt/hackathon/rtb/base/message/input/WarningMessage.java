package de.hbt.hackathon.rtb.base.message.input;

public class WarningMessage extends InputMessage {

	private final WarningType warningType;
	private final String message;

	private WarningMessage(WarningType warningType, String message) {
		this.warningType = warningType;
		this.message = message;
	}

	public WarningType getWarningType() {
		return warningType;
	}

	public String getMessage() {
		return message;
	}
	
	public static WarningMessage valueOf(String[] args){
		int warningType = Integer.parseInt(args[1]);
		return new WarningMessage(WarningType.valueOf(warningType), args[2]);
	}

}
