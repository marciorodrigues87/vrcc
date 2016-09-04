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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bottomRight == null) ? 0 : bottomRight.hashCode());
		result = prime * result + ((upperLeft == null) ? 0 : upperLeft.hashCode());
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
		Boundaries other = (Boundaries) obj;
		if (bottomRight == null) {
			if (other.bottomRight != null)
				return false;
		} else if (!bottomRight.equals(other.bottomRight))
			return false;
		if (upperLeft == null) {
			if (other.upperLeft != null)
				return false;
		} else if (!upperLeft.equals(other.upperLeft))
			return false;
		return true;
	}

}
