package de.hbt.hackathon.rtb.protocol.type;

public enum ObjectType {

	NOOBJECT(1), ROBOT(0), SHOT(1), WALL(2), COOKIE(3), MINE(4), LAST_OBJECT_TYPE(5);

	private final int code;

	private ObjectType(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
	
	public static ObjectType valueOf(int code) {
		for (ObjectType objectType: ObjectType.values()) {
			if (objectType.getCode() == code) {
				return objectType;
			}
		}
		throw new IllegalArgumentException("Unknown code for ObjectType: " + code);
	}

}
