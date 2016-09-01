package com.vrcc.api.servlet.impl;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import com.vrcc.api.servlet.RequestConverter;
import com.vrcc.domain.Property;
import com.vrcc.domain.PropertyFilter;
import com.vrcc.utils.json.JsonProvider;

public class SimpleRequestConverter implements RequestConverter {

	private final JsonProvider json;

	@Inject
	public SimpleRequestConverter(JsonProvider json) {
		this.json = json;
	}

	public Property property(HttpServletRequest request) throws IOException {
		return json.from(request.getInputStream(), Property.class);
	}

	public long propertyId(HttpServletRequest request) {
		return parseLong(request.getPathInfo().substring(1));
	}

	public PropertyFilter filter(HttpServletRequest request) {
		return new PropertyFilter(parseInt(request.getParameter("ax")),
				parseInt(request.getParameter("ay")),
				parseInt(request.getParameter("bx")),
				parseInt(request.getParameter("by")));
	}

}
