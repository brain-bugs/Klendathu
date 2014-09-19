package de.hbt.hackathon.rtb.strategy;

import org.fest.assertions.Delta;
import org.junit.Test;

import de.hbt.hackathon.rtb.base.type.Coordinate;

import static org.fest.assertions.Assertions.assertThat;

public class TargeterTest {

	@Test
	public void should_calculate_target_angle_1() {
		Targeter targeter = new Targeter();
		targeter.setOwnLocation(new Coordinate(0d, 0d, 0L), 0d);
		assertThat(targeter.calculateAngleTowards(new Coordinate(1d, 0d, 0L))).isEqualTo(0d, Delta.delta(1E-04));
		assertThat(targeter.calculateAngleTowards(new Coordinate(-1d, 0d, 0L))).isEqualTo(Math.toRadians(180), Delta.delta(1E-04));
		assertThat(targeter.calculateAngleTowards(new Coordinate(0d, 1d, 0L))).isEqualTo(Math.toRadians(90), Delta.delta(1E-04));
		assertThat(targeter.calculateAngleTowards(new Coordinate(0d, -1d, 0L))).isEqualTo(Math.toRadians(270), Delta.delta(1E-04));
	}

	@Test
	public void should_calculate_target_angle_2() {
		Targeter targeter = new Targeter();
		targeter.setOwnLocation(new Coordinate(-1d, 2d, 0L), 0.25d * Math.PI);
		assertThat(targeter.calculateAngleTowards(new Coordinate(2d, 5d, 0L))).isEqualTo(0d, Delta.delta(1E-04));
		assertThat(targeter.calculateAngleTowards(new Coordinate(-2d, 3d, 0L))).isEqualTo(Math.toRadians(90), Delta.delta(1E-04));
		assertThat(targeter.calculateAngleTowards(new Coordinate(-2d, 1d, 0L))).isEqualTo(Math.toRadians(180), Delta.delta(1E-04));
		assertThat(targeter.calculateAngleTowards(new Coordinate(2d, -1d, 0L))).isEqualTo(Math.toRadians(270), Delta.delta(1E-04));
	}

}
