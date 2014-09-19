package de.hbt.hackathon.rtb.strategy;

/**
 * Utility methods for computing and comparing angles in a Cartesian coordinate
 * system.
 * 
 * @author <a href="mailto:ah@hbt.de">Andre Hegerath</a>
 */
public final class AngleUtils {

	/**
	 * Private constructor to avoid instantiation.
	 */
	private AngleUtils() {
	}

	/**
	 * Calculates the angle of that line that connects the two given points
	 * (that starts at the point with the coordinates <code>x1, y1</code> and
	 * ends at the point with the coordinates <code>x2, y2</code>) with the
	 * positive x-axis in a Cartesian coordinate system. Angles increase
	 * counter-clockwise and are represented in radians between <code>0</code>
	 * and <code>2 * Math.PI</code>.
	 * <p/>
	 * Special case: if the two given points are equal, so that the line that
	 * connects them degenerates to a point, the angle is undefined.
	 * <code>0</code> is returned in that case.
	 * 
	 * @param x1
	 *            the abscissa (x-coordinate) of the first given point
	 * @param y1
	 *            the ordinate (y-coordinate) of the first given point
	 * @param x2
	 *            the abscissa (x-coordinate) of the second given point
	 * @param y2
	 *            the ordinate (y-coordinate) of the second given point
	 * @return the angle of the connecting line with the positive x-axis, in
	 *         radians
	 */
	public static double getAngle(double x1, double y1, double x2, double y2) {
		int cmpX = Double.compare(x1, x2);
		int cmpY = Double.compare(y1, y2);
		if (cmpX == 0) {
			if (cmpY > 0) {
				return 3 * Math.PI / 2;
			}
			if (cmpY < 0) {
				return 1 * Math.PI / 2;
			}
			return 0;
		}
		double angle;
		if (cmpX < 0) {
			angle = Math.atan((y2 - y1) / (x2 - x1));
		} else {
			angle = Math.PI + Math.atan((y2 - y1) / (x2 - x1));
		}
		return normalizeAngle(angle);
	}

	/**
	 * Calculates the difference between the two given angles, which is a value
	 * in radians between <code>-Math.PI</code> and <code>Math.PI</code>.
	 * <p/>
	 * Informally speaking, the returned value is larger than <code>0</code> if
	 * <code>angle2</code> is reached by rotating <code>angle1</code> in
	 * counter-clockwise direction for at most 180 degrees. It is less than zero
	 * if <code>angle2</code> is reached by rotating <code>angle1</code> in
	 * clockwise direction for at most 180 degrees instead. If both angles are
	 * equal, i.e. if <code>Double.compare(angle1, angle2)</code> returns
	 * <code>0</code>, this method returns <code>0</code>.
	 * 
	 * @param angle1
	 *            the first given angle
	 * @param angle2
	 *            the second given angle
	 * @return difference between the given angles, in radians
	 */
	public static double getAngleDifference(double angle1, double angle2) {
		double angle = angle2 - angle1;
		while (Double.compare(angle, -Math.PI) < 0) {
			angle += Math.PI * 2;
		}
		while (Double.compare(angle, Math.PI) > 0) {
			angle -= Math.PI * 2;
		}
		return angle;
	}

	/**
	 * Returns the mean angle of the two given angles, in radians between
	 * <code>0/<code> and <code>2 * Math.PI</code>. The mean angle is not
	 * necessarily the arithmetic mean of the given angles, as angles
	 * "wrap around" at 360 degrees (<code>2 * Math.PI</code>). For example, the
	 * mean angle of <code>315</code> degrees ~= <code>1.75 * Math.PI</code>
	 * radians and <code>90</code> degrees ~= <code>0.5 * Math.PI</code> radians
	 * is <code>22.5</code> degrees ~= <code>0.125 * Math.PI</code>.
	 * <p/>
	 * Informally speaking, the mean angle is reached by rotating the first
	 * given angle "half-way" towards the second given angle, in whichever
	 * direction is closer.
	 * 
	 * @param angle1
	 *            the first given angle
	 * @param angle2
	 *            the second given angle
	 * @return the mean angle between the two given angles
	 */
	public static double getMeanAngle(double angle1, double angle2) {
		double angle1Normalized = normalizeAngle(angle1);
		double angle2Normalized = normalizeAngle(angle2);
		int cmp1 = Double.compare(angle1Normalized, angle2Normalized);
		if (cmp1 == 0) { // angle1 == angle2
			return angle1Normalized;
		}
		double smallAngle = Math.min(angle1Normalized, angle2Normalized);
		double largeAngle = Math.max(angle1Normalized, angle2Normalized);
		double diff = largeAngle - smallAngle;
		int cmp2 = Double.compare(diff, Math.PI);
		if (cmp2 == 0) {
			return smallAngle + Math.PI / 2;
		} else if (cmp2 < 0) {
			return (smallAngle + largeAngle) / 2;
		} else {
			double half = (2 * Math.PI - largeAngle + smallAngle) / 2;
			return normalizeAngle(smallAngle - half);
		}
	}

	/**
	 * Normalizes the given angle so that it is larger than or equal to
	 * <code>0</code>, and less than <code>2 * Math.PI</code>.
	 * 
	 * @param angle
	 *            the angle to normalize, in radians
	 * @return the normalized angle, in radians
	 */
	public static double normalizeAngle(double angle) {
		double normalizedAngle = angle;
		while (Double.compare(0.0, normalizedAngle) > 0) {
			normalizedAngle += 2 * Math.PI;
		}
		while (Double.compare(2 * Math.PI, normalizedAngle) <= 0) {
			normalizedAngle -= 2 * Math.PI;
		}
		return normalizedAngle;
	}

	public static boolean isAngleInInterval(double fromAngle, boolean fromOpen, double toAngle, boolean toOpen, double angle,
			double precision, boolean clockwise) {
		double fromAngleNormalized = normalizeAngle(fromAngle);
		double toAngleNormalized = normalizeAngle(toAngle);
		double angleNormalized = normalizeAngle(angle);
		double distanceToFromAngle = Math.abs(getAngleDifference(fromAngleNormalized, angleNormalized));
		double distanceToToAngle = Math.abs(getAngleDifference(toAngleNormalized, angleNormalized));
		if ((Double.compare(precision, distanceToFromAngle) >= 0) && !fromOpen) {
			return true;
		} else if ((Double.compare(precision, distanceToToAngle) >= 0) && !toOpen) {
			return true;
		} else if ((Double.compare(precision, distanceToFromAngle) >= 0) || (Double.compare(precision, distanceToToAngle) >= 0)) {
			return false;
		}
		if (clockwise) {
			if (Double.compare(fromAngleNormalized, toAngleNormalized) > 0) {
				return (Double.compare(fromAngleNormalized, angleNormalized) > 0)
						&& (Double.compare(angleNormalized, toAngleNormalized) > 0);
			}
			return (Double.compare(fromAngleNormalized, angleNormalized) > 0) || (Double.compare(angleNormalized, toAngleNormalized) > 0);
		}
		if (Double.compare(fromAngleNormalized, toAngleNormalized) < 0) {
			return (Double.compare(fromAngleNormalized, angleNormalized) < 0) && (Double.compare(angleNormalized, toAngleNormalized) < 0);
		}
		return (Double.compare(fromAngleNormalized, angleNormalized) < 0) || (Double.compare(angleNormalized, toAngleNormalized) < 0);
	}

	public static boolean isAngleInOpenInterval(double fromAngle, double toAngle, double angle, double precision, boolean clockwise) {
		return isAngleInInterval(fromAngle, true, toAngle, true, angle, precision, clockwise);
	}

	public static boolean isAngleInClosedInterval(double fromAngle, double toAngle, double angle, double precision, boolean clockwise) {
		return isAngleInInterval(fromAngle, false, toAngle, false, angle, precision, clockwise);
	}

}
