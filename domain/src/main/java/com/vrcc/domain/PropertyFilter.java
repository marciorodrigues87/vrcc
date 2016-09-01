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

}
