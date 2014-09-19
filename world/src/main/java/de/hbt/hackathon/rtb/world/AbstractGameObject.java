package de.hbt.hackathon.rtb.world;

import de.hbt.hackathon.rtb.base.Coordinate;

public abstract class AbstractGameObject implements GameObject {

	private final Coordinate coordinate;
	
	public AbstractGameObject(Coordinate coordinate) {
		this.coordinate = coordinate;
	}

	public Coordinate getCoordinate() {
		return this.coordinate;
	}

}
