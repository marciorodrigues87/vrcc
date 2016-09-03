package com.vrcc.utils.math;

public class Point {

	private int x;
	private int y;

	private Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public static Point at(int x, int y) {
		return new Point(x, y);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

}
