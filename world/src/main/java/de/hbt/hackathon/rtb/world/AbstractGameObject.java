package de.hbt.hackathon.rtb.world;

import de.hbt.hackathon.rtb.base.Coordinate;

public abstract class AbstractGameObject implements GameObject {

	private final Coordinate currentPosition;
	
	public AbstractGameObject(Coordinate coordinate) {
		this.currentPosition = coordinate;
	}

	public Coordinate getCoordinate() {
		return this.currentPosition;
	}

}
