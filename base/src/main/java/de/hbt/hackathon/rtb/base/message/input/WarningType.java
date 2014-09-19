package de.hbt.hackathon.rtb.base.message.input;


public enum WarningType {

	UNKNOWN_MESSAGE(0), PROCESS_TIME_LOW(1), MESSAGE_SENT_IN_ILLEGAL_STATE(2), //
	UNKNOWN_OPTION(3), OBSOLETE_KEYWORD(4), NAME_NOT_GIVEN(5), COLOUR_NOT_GIVEN(6);

	private final int code;

	private WarningType(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static WarningType valueOf(int code) {
		WarningType[] values = values();
		for (WarningType warningType : values) {
			if(warningType.code == code) {
				return warningType;
			}
		}
		throw new IllegalArgumentException("Invalid code: " + code);
	}

}
