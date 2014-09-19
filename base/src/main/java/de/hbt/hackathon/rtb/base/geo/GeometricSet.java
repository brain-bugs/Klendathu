package de.hbt.hackathon.rtb.base.geo;

import java.util.Iterator;
import java.util.Set;

import com.vividsolutions.jts.geom.Geometry;

/**
 * Extension of a {@link Set} to build and maintain an index on geometric
 * objects. That index offers two methods that allow to iterate over those
 * geometric objects that intersect a given geometry (according to the
 * {@link Geometry#intersection(Geometry)} method) which acts as a "filter":
 * <ul>
 * <li><code>Iterator<E> iterator(Geometry filterGeometry)</code></li>
 * <li>
 * <code>Iterator<E> iterator(Geometry centerGeometry, Geometry filterGeometry)</code>
 * </li>
 * </ul>
 * In contrast to the iterator returned by the first method, the iterator
 * returned by the second method allows to iterate over the geometric objects
 * that intersect the given <code>filterGeometry</code> sorted by their distance
 * to another given "center" geometry.
 * <p/>
 * 
 * @see http 
 *      ://edndoc.esri.com/arcsde/9.1/general_topics/understand_spatial_relations
 *      .htm#Intersects
 * @author <a href="mailto:ah@hbt.de">Andre Hegerath</a>
 * @param <E>
 *            the geometric object type
 */
public interface GeometricSet<E extends GeoObject> extends Set<E> {

	/**
	 * Returns an area (polygon) that covers all the geometric objects contained
	 * in this set. The returned area should be as compact as it is reasonably
	 * possible to compute. Implementing classes should state which area is
	 * returned (e.g. bounding box, outer convex hull, or union, of the areas of
	 * the contained objects), and whether the returned area is always coherent
	 * or may be a collection of multiple unconnected polygons instead.
	 * 
	 * @return the area covered by this set of geometric objects
	 */
	Geometry getCoveredGeometry();

	/**
	 * Returns an iterator over all geometric objects that intersect the given
	 * geometry. The iterator does not guarantee any order on the returned
	 * objects. If the given geometry is <code>null</code>, all geometric
	 * objects of this set are returned by the iterator.
	 * 
	 * @param filterGeometry
	 *            the geometry that intersects the objects to be returned by the
	 *            iterator, may be <code>null</code>
	 * @return the iterator over the geometry objects
	 */
	Iterator<E> iterator(Geometry filterGeometry);

	/**
	 * Returns an iterator over all geometric objects that intersect the given
	 * "filter" geometry. The iterator returns the objects by their distance
	 * towards the given "center" geometry (in ascending order). If two objects
	 * returned by the iterator have the same distance (e.g. zero distance) to
	 * the center geometry, the order in which they are returned is undefined.
	 * If the given "filter" geometry is <code>null</code>, all geometric
	 * objects of this set are returned by the iterator. If the given "center"
	 * geometry is <code>null</code>, the resulting iterator is the same as
	 * returned by calling the {@link #iterator(Geometry)} method.
	 * 
	 * @param centerGeometry
	 *            the geometry whose distance orders the objects to be returned
	 *            by the iterator, may be <code>null</code>
	 * @param filterGeometry
	 *            the geometry that intersects the objects to be returned by the
	 *            iterator, may be <code>null</code>
	 * @return
	 */
	Iterator<E> iterator(Geometry centerGeometry, Geometry filterGeometry);

}
