package de.hbt.hackathon.rtb.base.message.input;

import de.hbt.hackathon.rtb.base.type.ObjectType;

public class CollisionMessage extends InputMessage {

	private final ObjectType collidingObjectType;
	private final double relativeCollisionAngle;

	private CollisionMessage(ObjectType objectType, double relativeCollisionAngle) {
		this.collidingObjectType = objectType;
		this.relativeCollisionAngle = relativeCollisionAngle;
	}

	public static CollisionMessage valueOf(String[] args) {
		ObjectType collidingObjectType = ObjectType.fromCode(Integer.parseInt(args[1]));
		double relativeCollisionAngle = Double.parseDouble(args[2]);
		return new CollisionMessage(collidingObjectType, relativeCollisionAngle);
	}

	public ObjectType getCollidingObjectType() {
		return collidingObjectType;
	}

	public double getRelativeCollisionAngle() {
		return relativeCollisionAngle;
	}
}
