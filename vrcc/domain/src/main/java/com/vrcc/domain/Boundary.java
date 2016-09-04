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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Boundary other = (Boundary) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

}
