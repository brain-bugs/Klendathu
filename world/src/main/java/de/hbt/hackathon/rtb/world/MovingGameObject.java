package de.hbt.hackathon.rtb.world;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections4.queue.CircularFifoQueue;

import de.hbt.hackathon.rtb.base.Coordinate;

public abstract class MovingGameObject implements GameObject {

	private final CircularFifoQueue<Coordinate> lastKnownPositions = new CircularFifoQueue<Coordinate>();

	public MovingGameObject(Coordinate currentPosition) {
		lastKnownPositions.offer(currentPosition);
	}

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