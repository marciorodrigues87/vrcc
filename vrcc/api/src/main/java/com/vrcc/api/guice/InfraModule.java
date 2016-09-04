package com.vrcc.api.guice;

import javax.sql.DataSource;

import com.google.inject.AbstractModule;
import com.vrcc.infra.dao.PropertyDAO;
import com.vrcc.infra.dao.ProvinceDAO;
import com.vrcc.infra.dao.impl.PropertyDAOJdbcImpl;
import com.vrcc.infra.dao.impl.ProvinceDAOFileImpl;
import com.vrcc.utils.json.Jackson;
import com.vrcc.utils.json.JsonProvider;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class InfraModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(DataSource.class).toInstance(new HikariDataSource(new HikariConfig("/hikari.properties")));
		bind(PropertyDAO.class).to(PropertyDAOJdbcImpl.class);
		bind(ProvinceDAO.class).to(ProvinceDAOFileImpl.class);
		bind(JsonProvider.class).to(Jackson.class);
	}

}
