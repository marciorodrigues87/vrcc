package com.vrcc.api.guice;

import com.google.inject.AbstractModule;
import com.vrcc.infra.dao.PropertyDAO;
import com.vrcc.infra.dao.ProvinceDAO;
import com.vrcc.infra.dao.impl.PropertyDAODataBaseImpl;
import com.vrcc.infra.dao.impl.ProvinceDAOFileImpl;
import com.vrcc.infra.db.SimpleDataSource;
import com.vrcc.infra.db.impl.MySqlDataSource;
import com.vrcc.utils.json.Jackson;
import com.vrcc.utils.json.JsonProvider;

public class InfraModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(SimpleDataSource.class).to(MySqlDataSource.class);
		bind(PropertyDAO.class).to(PropertyDAODataBaseImpl.class);
		bind(ProvinceDAO.class).to(ProvinceDAOFileImpl.class);
		bind(JsonProvider.class).to(Jackson.class);
	}

}
