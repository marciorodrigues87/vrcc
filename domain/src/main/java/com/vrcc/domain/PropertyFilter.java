package com.vrcc.domain;

import javax.ws.rs.QueryParam;

import com.vrcc.domain.validation.X;
import com.vrcc.domain.validation.Y;

public class PropertyFilter {

	@QueryParam("ax")
	private int ax;

	@QueryParam("ay")
	private int ay;

	@QueryParam("bx")
	private int bx;

	@QueryParam("by")
	private int by;

	public PropertyFilter() {
		this(0, 0, 0, 0);
	}

	public PropertyFilter(int ax, int ay, int bx, int by) {
		this.ax = ax;
		this.ay = ay;
		this.bx = bx;
		this.by = by;
	}

	@X
	public int getAx() {
		return ax;
	}

	@Y
	public int getAy() {
		return ay;
	}

	@X
	public int getBx() {
		return bx;
	}

	@Y
	public int getBy() {
		return by;
	}

}
