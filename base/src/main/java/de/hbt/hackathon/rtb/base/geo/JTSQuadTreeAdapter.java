package de.hbt.hackathon.rtb.base.geo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;

/**
 * Implementation of a geometric set backed by a Quadtree implementation of the
 * Java Topology Suite (JTS). The implementation is not thread-safe.
 * 
 * @author <a href="mailto:ah@hbt.de">Andre Hegerath</a>
 * @param <E>
 *            the geometric object type
 */
public class JTSQuadTreeAdapter<E extends GeoObject> implements GeometricSet<E> {

	private static final GeometryFactory GEOMETRY_FACTORY = new GeometryFactory();

	private final com.vividsolutions.jts.index.quadtree.Quadtree jtsQuadTree;

	private Geometry boundingBox;

	/**
	 * Creates a new geometric set.
	 */
	public JTSQuadTreeAdapter() {
		this.jtsQuadTree = new com.vividsolutions.jts.index.quadtree.Quadtree();
		this.boundingBox = null;
	}

	@Override
	public int size() {
		return jtsQuadTree.size();
	}

	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	@Override
	public boolean contains(Object obj) {
		if (!(obj instanceof GeoObject)) {
			return false;
		}
		GeoObject geoObject = (GeoObject) obj;
		Iterator<E> iterator = iterator(geoObject.getGeometry(), geoObject.getGeometry());
		while (iterator.hasNext()) {
			GeoObject other = iterator.next();
			if (Double.compare(1E-04, other.getGeometry().distance(geoObject.getGeometry())) < 0) {
				return false;
			}
			if (other.getClass() == geoObject.getClass()) {
				return true;
			}
		}
		return false;
	}

	public boolean intersectsGeometry(Geometry geometry) {
		final List<?> objects = jtsQuadTree.query(geometry.getEnvelopeInternal());
		if (objects != null) {
			for (Object obj : objects) {
				assert obj instanceof GeoObject;
				GeoObject geoObject = (GeoObject) obj;
				if (geoObject.getGeometry().intersects(geometry)) {
					return true;
				}
			}
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterator<E> iterator() {
		List<E> objects = new ArrayList<E>(jtsQuadTree.queryAll());
		return objects.iterator();
	}

	@Override
	public Object[] toArray() {
		return toArray(null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T[] toArray(T[] existingArray) {
		T[] array = existingArray;
		if (size() > array.length) {
			array = (T[]) new Geometry[size()];
		}
		int count = 0;
		for (Iterator<E> i = iterator(); i.hasNext();) {
			array[count] = (T) i.next();
		}
		if (array.length > size()) {
			array[size()] = null;
		}
		return array;
	}

	@Override
	public boolean add(E obj) {
		if (!contains(obj)) {
			jtsQuadTree.insert(obj.getGeometry().getEnvelopeInternal(), obj);
			boundingBox = null; // clear the bounding box, will be recalculated
								// on demand
			return true;
		}
		return false;
	}

	@Override
	public boolean remove(Object obj) {
		// if (contains(obj)) {
		boolean removed = jtsQuadTree.remove(((GeoObject) obj).getGeometry().getEnvelopeInternal(), obj);
		if (removed)
			boundingBox = null; // clear the bounding box, will be recalculated
								// on demand
		return removed;
		// }
		// return false;
	}

	@Override
	public boolean containsAll(Collection<?> coll) {
		for (Object object : coll) {
			if (!contains(object)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends E> coll) {
		boolean modified = false;
		for (E geom : coll) {
			modified = add(geom) || modified;
		}
		return modified;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean retainAll(Collection<?> coll) {
		boolean modified = false;
		for (Object object : coll) {
			if (object instanceof GeoObject) {
				modified |= !contains(object);
			}
		}
		for (Iterator<E> i = iterator(); i.hasNext();) {
			E geo = i.next();
			modified |= !coll.contains(geo);
		}
		if (modified) {
			clear();
			for (Object object : coll) {
				add((E) object);
			}
		}
		return modified;
	}

	@Override
	public boolean removeAll(Collection<?> coll) {
		boolean modified = false;
		for (Object object : coll) {
			modified = remove(object) || modified;
		}
		return modified;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void clear() {
		List<E> objects = new ArrayList<E>(jtsQuadTree.queryAll());
		for (E obj : objects) {
			jtsQuadTree.remove(obj.getGeometry().getEnvelopeInternal(), obj);
		}
		boundingBox = null;
	}

	/**
	 * Returns the common bounding box of all geometric objects contained in
	 * this set.
	 * 
	 * @return the bounding box
	 */
	@Override
	public Geometry getCoveredGeometry() {
		if (boundingBox == null) {
			for (Iterator<E> i = iterator(null); i.hasNext();) {
				Geometry g = i.next().getGeometry().getEnvelope();
				if (boundingBox == null) {
					boundingBox = g;
				} else {
					boundingBox = boundingBox.union(g).getEnvelope();
				}
			}
		}
		if (boundingBox == null)
			return GEOMETRY_FACTORY.createPoint((Coordinate) null);

		return boundingBox;
	}

	@Override
	public Iterator<E> iterator(Geometry filterGeometry) {
		return iterator(null, filterGeometry);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Iterator<E> iterator(Geometry centerGeometry, Geometry filterGeometry) {
		if (filterGeometry == null) {
			return iterator();
		}
		final Set<E> matchingElements = new HashSet<E>();
		final List<?> objects = jtsQuadTree.query(filterGeometry.getEnvelopeInternal());
		final Map<E, Double> distancesMap = new HashMap<E, Double>();

		if (objects != null) {
			for (Object obj : objects) {
				assert obj instanceof GeoObject;
				GeoObject geoObject = (GeoObject) obj;
				if (geoObject.getGeometry().intersects(filterGeometry)) {
					matchingElements.add((E) geoObject);
					if (centerGeometry != null) {
						distancesMap.put((E) geoObject, geoObject.getGeometry().distance(centerGeometry));
					}
				}
			}
		}

		if (centerGeometry != null) {
			List<E> sortedMatchingElements = new ArrayList<E>(matchingElements);
			Collections.sort(sortedMatchingElements, new Comparator<E>() {
				@Override
				public int compare(E o1, E o2) {
					return distancesMap.get(o1).compareTo(distancesMap.get(o2));
				}
			});
			return sortedMatchingElements.iterator();
		}
		return matchingElements.iterator();
	}

	@Override
	public String toString() {
		return "quadtree with " + size() + " geo objects";
	}
}
