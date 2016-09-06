package com.vrcc.api.guice;

import static com.google.inject.matcher.Matchers.annotatedWith;
import static com.google.inject.matcher.Matchers.any;
import static com.vrcc.utils.hibernate.HibernateSessionFactoryBuilder.sessionFactoryBuilder;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.AbstractModule;
import com.lambdaworks.redis.RedisClient;
import com.vrcc.infra.dao.PropertyDAO;
import com.vrcc.infra.dao.ProvinceDAO;
import com.vrcc.infra.dao.impl.PropertyDAOHibernateImpl;
import com.vrcc.infra.dao.impl.ProvinceDAOFileImpl;
import com.vrcc.infra.dao.impl.entity.PropertyEntity;
import com.vrcc.infra.dao.impl.entity.PropertyProvinceEntity;
import com.vrcc.utils.cache.Cache;
import com.vrcc.utils.cache.CacheInterceptor;
import com.vrcc.utils.cache.CacheKeyGenerator;
import com.vrcc.utils.cache.Cached;
import com.vrcc.utils.cache.impl.MethodSignatureGenerator;
import com.vrcc.utils.cache.impl.Redis;
import com.vrcc.utils.hibernate.HibernateTransactionInterceptor;
import com.vrcc.utils.hibernate.SessionContext;
import com.vrcc.utils.hibernate.Transactional;
import com.vrcc.utils.json.Jackson;
import com.vrcc.utils.json.JsonProvider;
import com.vrcc.utils.serialization.NativeSerialization;
import com.vrcc.utils.serialization.SerializationProvider;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class InfraModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(PropertyDAO.class).to(PropertyDAOHibernateImpl.class);
		bind(ProvinceDAO.class).to(ProvinceDAOFileImpl.class);
		final CacheInterceptor cacheInterceptor = new CacheInterceptor();
		requestInjection(cacheInterceptor);
		bindInterceptor(any(), annotatedWith(Cached.class), cacheInterceptor);
		bind(JsonProvider.class).to(Jackson.class);
		bind(ObjectMapper.class);
		bind(SerializationProvider.class).to(NativeSerialization.class);
		bind(CacheKeyGenerator.class).to(MethodSignatureGenerator.class);
		bind(RedisClient.class).toInstance(redisClient());
		bind(Cache.class).to(Redis.class);
		final HikariDataSource dataSource = dataSource();
		bind(DataSource.class).toInstance(dataSource);
		bind(SessionFactory.class).toInstance(sessionFactory(dataSource));
		bind(SessionContext.class);
		final HibernateTransactionInterceptor transactionInterceptor = new HibernateTransactionInterceptor();
		requestInjection(transactionInterceptor);
		bindInterceptor(any(), annotatedWith(Transactional.class), transactionInterceptor);
	}

	private SessionFactory sessionFactory(final HikariDataSource dataSource) {
		return sessionFactoryBuilder()
				.addAnnotatedClass(PropertyEntity.class)
				.addAnnotatedClass(PropertyProvinceEntity.class)
				.withDataSource(dataSource)
				.build();
	}

	private RedisClient redisClient() {
		return RedisClient.create("redis://cache");
	}

	private HikariDataSource dataSource() {
		return new HikariDataSource(new HikariConfig("/hikari.properties"));
	}

}
