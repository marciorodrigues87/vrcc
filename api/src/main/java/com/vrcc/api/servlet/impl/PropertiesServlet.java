package com.vrcc.api.servlet.impl;

import static com.vrcc.utils.Regexes.NUMERIC;
import static java.lang.String.format;
import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;

import java.io.IOException;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.vrcc.api.servlet.PropertiesResource;
import com.vrcc.api.servlet.RequestConverter;
import com.vrcc.api.servlet.ResponseWriter;
import com.vrcc.core.facade.PropertyFacade;
import com.vrcc.core.facade.impl.SimplePropertyFacade;
import com.vrcc.domain.Properties;
import com.vrcc.domain.Property;
import com.vrcc.domain.PropertyFilter;

@Singleton
public class PropertiesServlet extends HttpServlet implements
		PropertiesResource {

	private static final long serialVersionUID = 1L;

	private static final String PROPERTIES_URI = "/properties";
	private static final String PROPERTY_URI = format("%s/%s", PROPERTIES_URI,
			NUMERIC);

	private final PropertyFacade facade;
	private final RequestConverter converter;
	private final ResponseWriter writer;

	@Inject
	public PropertiesServlet(SimplePropertyFacade facade,
			SimpleRequestConverter converter, SimpleResponseWriter writer) {
		this.facade = facade;
		this.converter = converter;
		this.writer = writer;
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		final Property property = converter.property(request);
		final Property addedProperty = add(property);
		writer.write(response, addedProperty);
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (request.getRequestURI().matches(PROPERTY_URI)) {
			final long propertyId = converter.propertyId(request);
			final Property property = get(propertyId);
			writer.write(response, property);
		} else if (request.getRequestURI().matches(PROPERTIES_URI)) {
			final PropertyFilter filter = converter.filter(request);
			final Properties properties = find(filter);
			writer.write(response, properties);
		} else {
			response.setStatus(SC_NOT_FOUND);
		}
	}

	@Override
	public Property add(Property request) {
		return facade.add(request);
	}

	@Override
	public Property get(long id) {
		return facade.get(id);
	}

	@Override
	public Properties find(PropertyFilter filter) {
		return facade.find(filter);
	}

}
