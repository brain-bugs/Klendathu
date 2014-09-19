package de.hbt.hackathon.rtb.base.message.output;

import java.util.EnumSet;

import de.hbt.hackathon.rtb.base.message.AngleType;

public class RotateAmountMessage extends RotateMessage {

	private final double relativeAngle;

	public RotateAmountMessage(EnumSet<AngleType> what, double rotationVelocity, double relativeAngle) {
		super(what, rotationVelocity);
		this.relativeAngle = relativeAngle;
	}

	public double getRelativeAngle() {
		return relativeAngle;
	}

}
