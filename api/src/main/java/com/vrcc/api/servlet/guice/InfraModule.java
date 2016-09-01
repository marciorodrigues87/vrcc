package com.vrcc.api.servlet.guice;

import com.google.inject.AbstractModule;
import com.vrcc.api.servlet.RequestConverter;
import com.vrcc.api.servlet.ResponseWriter;
import com.vrcc.api.servlet.impl.SimpleRequestConverter;
import com.vrcc.api.servlet.impl.SimpleResponseWriter;
import com.vrcc.utils.json.Jackson;
import com.vrcc.utils.json.JsonProvider;

public class InfraModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(JsonProvider.class).to(Jackson.class);
		bind(RequestConverter.class).to(SimpleRequestConverter.class);
		bind(ResponseWriter.class).to(SimpleResponseWriter.class);
	}

}
