package com.vrcc.api.guice;

import com.google.inject.AbstractModule;
import com.vrcc.infra.dao.PropertyDAO;
import com.vrcc.infra.dao.ProvinceDAO;
import com.vrcc.infra.dao.impl.PropertyDAOMemoryImpl;
import com.vrcc.infra.dao.impl.ProvinceDAOFileImpl;
import com.vrcc.utils.json.Jackson;
import com.vrcc.utils.json.JsonProvider;

public class InfraModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(PropertyDAO.class).to(PropertyDAOMemoryImpl.class);
		bind(ProvinceDAO.class).to(ProvinceDAOFileImpl.class);
		bind(JsonProvider.class).to(Jackson.class);
	}

}
