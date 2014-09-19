package de.hbt.hackathon.rtb.base.message.input;

import java.util.EnumSet;
import java.util.Set;

import de.hbt.hackathon.rtb.base.message.AngleType;

public class RotationReachedMessage extends InputMessage {

	private final Set<AngleType> reachedAngles;

	private RotationReachedMessage(EnumSet<AngleType> reachedAngles) {
		this.reachedAngles = reachedAngles;
	}

	public Set<AngleType> getReachedAngles() {
		return reachedAngles;
	}

	public static RotationReachedMessage valueOf(String[] args) {
		int codeCombination = Integer.valueOf(args[1]);
		EnumSet<AngleType> angles = EnumSet.noneOf(AngleType.class);
		for (AngleType angleType : AngleType.values()) {
			if ((codeCombination & angleType.getCode()) != 0) {
				angles.add(angleType);
			}
		}
		return new RotationReachedMessage(angles);
	}

}
