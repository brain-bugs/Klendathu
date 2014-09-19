package de.hbt.hackathon.rtb.world;

import de.hbt.hackathon.rtb.base.geo.GeoObject;
import de.hbt.hackathon.rtb.base.type.Coordinate;

public interface GameObject extends GeoObject {
	Coordinate getCurrentPosition();
}
