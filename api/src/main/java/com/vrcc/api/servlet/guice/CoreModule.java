package com.vrcc.api.servlet.guice;

import com.google.inject.AbstractModule;
import com.vrcc.core.dao.PropertyDAO;
import com.vrcc.core.dao.impl.PropertyDAOMemoryImpl;
import com.vrcc.core.facade.PropertyFacade;
import com.vrcc.core.facade.impl.SimplePropertyFacade;

public class CoreModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(PropertyFacade.class).to(SimplePropertyFacade.class);
		bind(PropertyDAO.class).to(PropertyDAOMemoryImpl.class);
	}

}
