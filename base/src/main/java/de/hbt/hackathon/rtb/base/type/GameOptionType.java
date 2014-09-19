package de.hbt.hackathon.rtb.base.type;

public enum GameOptionType {

	ROBOT_MAX_ROTATE(0), ROBOT_CANNON_MAX_ROTATE(1), ROBOT_RADAR_MAX_ROTATE(2),

	ROBOT_MAX_ACCELERATION(3), ROBOT_MIN_ACCELERATION(4),

	ROBOT_START_ENERGY(5), ROBOT_MAX_ENERGY(6), ROBOT_ENERGY_LEVELS(7),

	SHOT_SPEED(8), SHOT_MIN_ENERGY(9), SHOT_MAX_ENERGY(10), SHOT_ENERGY_INCREASE_SPEED(11),

	TIMEOUT(12),

	// 0 - no debug), 5 - highest debug level
	DEBUG_LEVEL(13),

	// 0 - no coordinates), 1 - coordniates are given relative the starting
	// position,2 - absolute coordinates
	SEND_ROBOT_COORDINATES(14);
	
	public static final double SEND_ROBOT_COORDINATES_NEVER = 0.0;
	public static final double SEND_ROBOT_COORDINATES_RELATIVE = 1.0;
	public static final double SEND_ROBOT_COORDINATES_ABSOLUTE = 2.0;

	private final int code;

	private GameOptionType(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
	
	public static GameOptionType valueOf(int code) {
		GameOptionType[] values = values();
		for (GameOptionType gameOptionType : values) {
			if(gameOptionType.code == code) {
				return gameOptionType;
			}
		}
		throw new IllegalArgumentException("Invalid code: " + code);
	}

}
