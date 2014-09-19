package de.hbt.hackathon.rtb.base.geo;

import com.vividsolutions.jts.geom.Geometry;

public class DefaultGeoObject implements GeoObject {

	private final Geometry geometry;

	public DefaultGeoObject(Geometry geometry) {
		this.geometry = geometry;
	}

	@Override
	public Geometry getGeometry() {
		return geometry;
	}
}
