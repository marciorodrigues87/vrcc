package com.vrcc.utils.math;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class RectangleTest {

	@Test
	public void shouldContainsPoint() {
		assertTrue(Rectangle.at(Point.at(400, 1000), Point.at(1100, 500)).contains(Point.at(800, 600)));
	}

	@Test
	public void shouldNotContainsPointBecausaOfXCoordinate() {
		assertFalse(Rectangle.at(Point.at(400, 1000), Point.at(1100, 500)).contains(Point.at(1400, 600)));
	}

	@Test
	public void shouldNotContainsPointBecausaOfYCoordinate() {
		assertFalse(Rectangle.at(Point.at(400, 1000), Point.at(1100, 500)).contains(Point.at(800, 100)));
	}

	@Test
	public void shouldNotContainsPointBecausaOfXAndYCoordinates() {
		assertFalse(Rectangle.at(Point.at(400, 1000), Point.at(1100, 500)).contains(Point.at(1400, 100)));
	}

}
