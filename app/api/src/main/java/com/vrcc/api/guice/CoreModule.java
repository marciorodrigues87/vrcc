package com.vrcc.api.guice;

import com.google.inject.AbstractModule;
import com.vrcc.core.business.PropertyAssembler;
import com.vrcc.core.business.ProvinceLocator;
import com.vrcc.core.facade.PropertyFacade;
import com.vrcc.core.facade.impl.SimplePropertyFacade;

public class CoreModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(PropertyFacade.class).to(SimplePropertyFacade.class);
		bind(PropertyAssembler.class);
		bind(ProvinceLocator.class);
	}

}
