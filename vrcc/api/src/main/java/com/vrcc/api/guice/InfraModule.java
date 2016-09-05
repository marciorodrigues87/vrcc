package com.vrcc.api.guice;

import static com.google.inject.matcher.Matchers.annotatedWith;
import static com.google.inject.matcher.Matchers.any;

import javax.sql.DataSource;

import com.google.inject.AbstractModule;
import com.lambdaworks.redis.RedisClient;
import com.vrcc.infra.dao.PropertyDAO;
import com.vrcc.infra.dao.ProvinceDAO;
import com.vrcc.infra.dao.impl.PropertyDAOJdbcImpl;
import com.vrcc.infra.dao.impl.ProvinceDAOFileImpl;
import com.vrcc.utils.cache.Cache;
import com.vrcc.utils.cache.CacheInterceptor;
import com.vrcc.utils.cache.CacheKeyGenerator;
import com.vrcc.utils.cache.Cached;
import com.vrcc.utils.cache.impl.MethodSignatureGenerator;
import com.vrcc.utils.cache.impl.Redis;
import com.vrcc.utils.json.Jackson;
import com.vrcc.utils.json.JsonProvider;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class InfraModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(PropertyDAO.class).to(PropertyDAOJdbcImpl.class);
		bind(ProvinceDAO.class).to(ProvinceDAOFileImpl.class);
		final CacheInterceptor cacheInterceptor = new CacheInterceptor();
		requestInjection(cacheInterceptor);
		bindInterceptor(any(), annotatedWith(Cached.class), cacheInterceptor);
		bind(JsonProvider.class).to(Jackson.class);
		bind(CacheKeyGenerator.class).to(MethodSignatureGenerator.class);
		bind(RedisClient.class).toInstance(RedisClient.create("redis://cache"));
		bind(Cache.class).to(Redis.class);
		bind(DataSource.class).toInstance(new HikariDataSource(new HikariConfig("/hikari.properties")));
	}

}
