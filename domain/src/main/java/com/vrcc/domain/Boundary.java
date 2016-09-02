package com.vrcc.domain;

public class Boundary {

	private final int x;
	private final int y;

	Boundary() {
		this(0, 0);
	}

	private Boundary(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public static Boundary at(int x, int y) {
		return new Boundary(x, y);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

}
