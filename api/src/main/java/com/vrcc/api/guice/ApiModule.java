package com.vrcc.api.guice;

import com.google.inject.AbstractModule;
import com.vrcc.api.PropertiesResource;
import com.vrcc.api.provider.ConstraintViolationExceptionMapper;
import com.vrcc.api.provider.DefaultExceptionMapper;

public class ApiModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(PropertiesResource.class);
		bind(DefaultExceptionMapper.class);
		bind(ConstraintViolationExceptionMapper.class);
	}

}
