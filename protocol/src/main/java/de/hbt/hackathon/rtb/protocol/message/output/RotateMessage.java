package de.hbt.hackathon.rtb.protocol.message.output;

import java.util.EnumSet;
import java.util.Set;

import de.hbt.hackathon.rtb.protocol.message.AngleType;

public class RotateMessage extends OutputMessage {

	private final Set<AngleType> what;
	private final double rotationVelocity;

	public RotateMessage(EnumSet<AngleType> what, double rotationVelocity) {
		this.what = what;
		this.rotationVelocity = rotationVelocity;
	}

	public Set<AngleType> getWhat() {
		return what;
	}

	public double getRotationVelocity() {
		return rotationVelocity;
	}

}
