package com.vrcc.domain;

public class Boundaries {

	private final Boundary upperLeft;
	private final Boundary bottomRight;

	Boundaries() {
		this(null, null);
	}

	public Boundaries(Boundary upperLeft, Boundary bottomRight) {
		this.upperLeft = upperLeft;
		this.bottomRight = bottomRight;
	}

	public Boundary getUpperLeft() {
		return upperLeft;
	}

	public Boundary getBottomRight() {
		return bottomRight;
	}

}
