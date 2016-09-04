package com.vrcc.api.domain;

import javax.ws.rs.QueryParam;

import com.vrcc.api.domain.validation.X;
import com.vrcc.api.domain.validation.Y;

public class PropertyFilterParam {

	@QueryParam("ax")
	private String ax;

	@QueryParam("ay")
	private String ay;

	@QueryParam("bx")
	private String bx;

	@QueryParam("by")
	private String by;

	public PropertyFilterParam() {
	}

	@X
	public String getAx() {
		return ax;
	}

	@Y
	public String getAy() {
		return ay;
	}

	@X
	public String getBx() {
		return bx;
	}

	@Y
	public String getBy() {
		return by;
	}

}
