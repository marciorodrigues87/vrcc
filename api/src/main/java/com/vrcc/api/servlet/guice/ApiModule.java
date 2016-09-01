package com.vrcc.api.servlet.guice;

import com.google.inject.servlet.ServletModule;
import com.vrcc.api.servlet.impl.PropertiesServlet;

public class ApiModule extends ServletModule {

	@Override
	protected void configureServlets() {
		serve("/properties*").with(PropertiesServlet.class);
	}

}
