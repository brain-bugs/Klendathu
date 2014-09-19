package de.hbt.hackathon.rtb.protocol.message.output;

import java.util.EnumSet;
import java.util.Set;

import de.hbt.hackathon.rtb.protocol.message.AngleType;

public class SweepMessage extends OutputMessage {
	
	private final Set<AngleType> what;
	private final double rotationVelocity;
	private final double leftAngle;
	private final double rightAngle;
	
	public SweepMessage(EnumSet<AngleType> what, double rotationVelocity, double leftAngle, double rightAngle) {
		this.what = what;
		this.rotationVelocity = rotationVelocity;
		this.leftAngle = leftAngle;
		this.rightAngle = rightAngle;
	}

	public Set<AngleType> getWhat() {
		return what;
	}

	public double getRotationVelocity() {
		return rotationVelocity;
	}

	public double getLeftAngle() {
		return leftAngle;
	}

	public double getRightAngle() {
		return rightAngle;
	}
	
}
