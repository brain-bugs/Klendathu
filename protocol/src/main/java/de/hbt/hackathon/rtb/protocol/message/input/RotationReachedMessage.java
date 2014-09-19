package de.hbt.hackathon.rtb.protocol.message.input;

import java.util.EnumSet;
import java.util.Set;

import de.hbt.hackathon.rtb.protocol.message.AngleType;

public class RotationReachedMessage {
	
	private final Set<AngleType> reachedAngles = EnumSet.noneOf(AngleType.class);

}
