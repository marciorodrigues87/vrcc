package com.vrcc.api.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.vrcc.domain.Property;
import com.vrcc.domain.PropertyFilter;

public interface RequestConverter {

	Property property(HttpServletRequest request) throws IOException;

	long propertyId(HttpServletRequest request);

	PropertyFilter filter(HttpServletRequest request);

}