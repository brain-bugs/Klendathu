package de.hbt.hackathon.rtb.world;

import java.util.Collection;
import java.util.Collections;

import org.apache.commons.collections4.queue.CircularFifoQueue;

import de.hbt.hackathon.rtb.base.geo.DefaultGeoObject;
import de.hbt.hackathon.rtb.base.geo.GeometryBuilder;
import de.hbt.hackathon.rtb.base.type.Coordinate;

public abstract class MovingGameObject extends DefaultGeoObject implements GameObject {

	private final CircularFifoQueue<Coordinate> lastKnownPositions = new CircularFifoQueue<Coordinate>();

	public MovingGameObject(Coordinate currentPosition) {
		super(GeometryBuilder.INSTANCE.apply(currentPosition));
		lastKnownPositions.offer(currentPosition);
	}

	public void setCurrentPosition(Coordinate coordinate) {
		lastKnownPositions.offer(coordinate);
	}

	@Override
	public Coordinate getCurrentPosition() {
		return lastKnownPositions.peek();
	}

	/**
	 * Returns a view of the last positions. The first position is the current
	 * position, then in temporal order.
	 */
	public Collection<Coordinate> getLastKnownPositions() {
		return Collections.unmodifiableCollection(lastKnownPositions);
	}

}