package de.hbt.hackathon.rtb.protocol.message;

public enum AngleType {
	
	ROBOT(1), CANNON(2), RADAR(4);
	
	private final int code;
	
	private AngleType(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}

}
