package de.hbt.hackathon.rtb.world;

import de.hbt.hackathon.rtb.base.Coordinate;
import de.hbt.hackathon.rtb.base.geo.GeoObject;

public interface GameObject extends GeoObject {
	Coordinate getCurrentPosition();
}
