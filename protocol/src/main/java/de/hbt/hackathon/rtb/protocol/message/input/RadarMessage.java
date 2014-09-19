package de.hbt.hackathon.rtb.protocol.message.input;

import de.hbt.hackathon.rtb.protocol.type.ObjectType;

public class RadarMessage extends InputMessage {

	private final double radarDistance;
	private final double radarAngle; // in radians
	private final ObjectType objectType;

	private RadarMessage(double radarDistance, double radarAngle, ObjectType objectType) {
		this.radarDistance = radarDistance;
		this.radarAngle = radarAngle;
		this.objectType = objectType;
	}
	
	public double getRadarDistance() {
		return radarDistance;
	}

	public double getRadarAngle() {
		return radarAngle;
	}

	public ObjectType getObjectType() {
		return objectType;
	}

	public static RadarMessage valueOf(String[] args) {
		double radarDistance = Double.valueOf(args[1]);
		double radarAngle = Double.valueOf(args[2]);
		ObjectType objectType = ObjectType.valueOf(args[3]);
		return new RadarMessage(radarDistance, radarAngle, objectType);
	}
	
}
