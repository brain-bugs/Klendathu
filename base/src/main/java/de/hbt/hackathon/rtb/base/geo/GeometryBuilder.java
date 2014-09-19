package de.hbt.hackathon.rtb.base.geo;

import com.google.common.base.Function;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;

import de.hbt.hackathon.rtb.base.Coordinate;

public class GeometryBuilder implements Function<Coordinate, Geometry> {

	public static final GeometryBuilder INSTANCE = new GeometryBuilder();

	private static final GeometryFactory GEO_FACTORY = new GeometryFactory();

	@Override
	public Geometry apply(Coordinate input) {
		com.vividsolutions.jts.geom.Coordinate jtsCoordinate = new com.vividsolutions.jts.geom.Coordinate(input.getX(), input.getY());
		Point point = GEO_FACTORY.createPoint(jtsCoordinate);
		return point;
	}

}
