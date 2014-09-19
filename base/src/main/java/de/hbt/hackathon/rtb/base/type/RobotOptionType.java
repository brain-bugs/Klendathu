package de.hbt.hackathon.rtb.base.type;

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
	
	public static final double SEND_SIGNAL_FALSE = 0.0;
	public static final double SEND_SIGNAL_TRUE = 1.0;
	
	public static final double SEND_ROTATION_REACHED_NEVER = 0.0;
	public static final double SEND_ROTATION_REACHED_AFTER_FINISHED = 1.0;
	public static final double SEND_ROTATION_REACHED_SWEEP_DIRECTION_CHANGES = 2.0;
	public static final double SEND_ROTATION_REACHED_ALL = 3.0;
	
	public static final double USE_NON_BLOCKING_FALSE = 0.0;
	public static final double USE_NON_BLOCKING_TRUE = 1.0;
	
	private final int code;
	
	private RobotOptionType(int code) {
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}

}
