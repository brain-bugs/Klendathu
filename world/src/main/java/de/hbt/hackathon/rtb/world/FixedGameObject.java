package de.hbt.hackathon.rtb.world;

import de.hbt.hackathon.rtb.base.Coordinate;

public abstract class FixedGameObject implements GameObject {

	private final Coordinate currentPosition;
	
	public FixedGameObject(Coordinate coordinate) {
		this.currentPosition = coordinate;
	}

	public Coordinate getCurrentPosition() {
		return this.currentPosition;
	}

}
