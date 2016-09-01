package com.vrcc.api.guice;

import com.google.inject.AbstractModule;
import com.vrcc.core.business.PropertyAssembler;
import com.vrcc.core.business.ProvinceLocator;
import com.vrcc.core.dao.PropertyDAO;
import com.vrcc.core.dao.ProvinceDAO;
import com.vrcc.core.dao.impl.PropertyDAOMemoryImpl;
import com.vrcc.core.dao.impl.ProvinceDAOFileImpl;
import com.vrcc.core.facade.PropertyFacade;
import com.vrcc.core.facade.impl.SimplePropertyFacade;

public class CoreModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(PropertyFacade.class).to(SimplePropertyFacade.class);
		bind(PropertyAssembler.class);
		bind(ProvinceLocator.class);
		bind(PropertyDAO.class).to(PropertyDAOMemoryImpl.class);
		bind(ProvinceDAO.class).to(ProvinceDAOFileImpl.class);
	}

}
