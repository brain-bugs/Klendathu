package de.hbt.hackathon.rtb.protocol.message.output;

import java.util.EnumSet;
import java.util.Set;

import de.hbt.hackathon.rtb.protocol.message.AngleType;

public class RotateMessage {
	
	private final Set<AngleType> what = EnumSet.noneOf(AngleType.class);
	private final double rotationVelocity;

}
