package de.hbt.hackathon.rtb.protocol.type;

public enum RobotOptionType {

	SIGNAL(2), // 0 - no signal), > 1 - signal to send (e.g. SIGUSR1 or SIGUSR2)
	SEND_SIGNAL(0), // 0 - false), 1 - true
	SEND_ROTATION_REACHED(1), // 0 - no messages
								// 1 - messages when RotateTo and RotateAmount
								// finished
								// 2 - messages also when sweep direction is
								// changed

	// 0 - false), 1 - true
	// This option should always be sent as soon as possible
	USE_NON_BLOCKING(3);
	
	private final int code;
	
	private RobotOptionType(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}

}
