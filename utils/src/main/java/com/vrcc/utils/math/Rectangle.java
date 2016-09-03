package com.vrcc.utils.math;

public class Rectangle {

	private final Point upperLeft;
	private final Point bottomRight;

	private Rectangle(Point upperLeft, Point bottomRight) {
		this.upperLeft = upperLeft;
		this.bottomRight = bottomRight;
	}

	public static Rectangle at(Point upperLeft, Point bottomRight) {
		return new Rectangle(upperLeft, bottomRight);
	}

	public boolean contains(Point point) {
		return point.getX() >= upperLeft.getX()
				&& point.getX() <= bottomRight.getX()
				&& point.getY() >= bottomRight.getY()
				&& point.getY() <= upperLeft.getY();
	}

}
