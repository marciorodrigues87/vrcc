package com.vrcc.domain;

public class PropertyFilter {

	private final int ax;
	private final int ay;
	private final int bx;
	private final int by;

	private PropertyFilter(int ax, int ay, int bx, int by) {
		this.ax = ax;
		this.ay = ay;
		this.bx = bx;
		this.by = by;
	}

	public static PropertyFilter full(int ax, int ay, int bx, int by) {
		return new PropertyFilter(ax, ay, bx, by);
	}

	public int getAx() {
		return ax;
	}

	public int getAy() {
		return ay;
	}

	public int getBx() {
		return bx;
	}

	public int getBy() {
		return by;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ax;
		result = prime * result + ay;
		result = prime * result + bx;
		result = prime * result + by;
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
		PropertyFilter other = (PropertyFilter) obj;
		if (ax != other.ax)
			return false;
		if (ay != other.ay)
			return false;
		if (bx != other.bx)
			return false;
		if (by != other.by)
			return false;
		return true;
	}

}
