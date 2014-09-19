package de.hbt.hackathon.rtb.base.message.output;

import java.util.EnumSet;

import de.hbt.hackathon.rtb.base.message.AngleType;

public class RotateToMessage extends RotateMessage {

	private final double endAngle;

	public RotateToMessage(EnumSet<AngleType> what, double rotationVelocity, double endAngle) {
		super(what, rotationVelocity);
		this.endAngle = endAngle;
	}

	public double getEndAngle() {
		return endAngle;
	}

}
