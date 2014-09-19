package de.hbt.hackathon.rtb.world;

import de.hbt.hackathon.rtb.base.geo.DefaultGeoObject;
import de.hbt.hackathon.rtb.base.geo.GeometryBuilder;
import de.hbt.hackathon.rtb.base.type.Coordinate;

public abstract class FixedGameObject extends DefaultGeoObject implements GameObject {

	private Coordinate currentPosition;

	public FixedGameObject(Coordinate coordinate) {
		super(GeometryBuilder.INSTANCE.apply(coordinate));
		this.currentPosition = coordinate;
	}

	@Override
	public Coordinate getCurrentPosition() {
		return this.currentPosition;
	}

	protected void setCurrentPosition(Coordinate currentCoordinate) {
		currentPosition = currentCoordinate;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "@" + getCurrentPosition();
	}

}
