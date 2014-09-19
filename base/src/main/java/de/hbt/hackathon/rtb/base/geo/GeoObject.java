package de.hbt.hackathon.rtb.base.geo;

import com.vividsolutions.jts.geom.Geometry;

/**
 * Interface for geometry objects that have a geometry and a unique identifier.
 * 
 * @author <a href="mailto:ah@hbt.de">Andre Hegerath</a>
 */
public interface GeoObject {

	/**
	 * Returns the geometry of this geo object.
	 * 
	 * @return the geometry
	 */
	Geometry getGeometry();

}
