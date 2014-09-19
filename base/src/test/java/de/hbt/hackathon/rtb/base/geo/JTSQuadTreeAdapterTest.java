package de.hbt.hackathon.rtb.base.geo;

import org.junit.Assert;
import org.junit.Test;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;

public class JTSQuadTreeAdapterTest {

	@Test
	public void testRemove() {
		JTSQuadTreeAdapter<DefaultGeoObject> tests = new JTSQuadTreeAdapter<DefaultGeoObject>();
		GeometryFactory gf = new GeometryFactory();
		DefaultGeoObject test1 = new DefaultGeoObject(gf.createPoint(new Coordinate(1, 1)));
		DefaultGeoObject test2 = new DefaultGeoObject(gf.createPoint(new Coordinate(2, 3)));
		DefaultGeoObject test3 = new DefaultGeoObject(gf.createPoint(new Coordinate(3, 3)));
		tests.add(test1);
		tests.add(test2);
		tests.add(test3);

		boolean removed = tests.remove(test2);
		Assert.assertEquals(true, removed);
		Assert.assertEquals(2, tests.size());

		removed = tests.remove(test2);
		Assert.assertEquals(false, removed);
		Assert.assertEquals(2, tests.size());

		removed = tests.remove(test3);
		Assert.assertEquals(true, removed);
		Assert.assertEquals(1, tests.size());
	}

}
