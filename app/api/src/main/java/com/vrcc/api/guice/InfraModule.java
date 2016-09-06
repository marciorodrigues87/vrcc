package com.vrcc.api.guice;

import static com.google.inject.matcher.Matchers.annotatedWith;
import static com.google.inject.matcher.Matchers.any;
import static com.vrcc.api.config.Config.CACHE_HOST;
import static com.vrcc.api.config.Config.DB_HOST;
import static com.vrcc.utils.hibernate.HibernateSessionFactoryBuilder.sessionFactoryBuilder;
import static java.lang.String.format;

import java.util.Properties;

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
import com.vrcc.utils.cache.Expires;
import com.vrcc.utils.cache.ExpiringCacheInterceptor;
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
		final ExpiringCacheInterceptor expiringCacheInterceptor = new ExpiringCacheInterceptor();
		requestInjection(expiringCacheInterceptor);
		bindInterceptor(any(), annotatedWith(Expires.class), expiringCacheInterceptor);
		bind(JsonProvider.class).to(Jackson.class);
		bind(ObjectMapper.class);
		bind(SerializationProvider.class).to(NativeSerialization.class);
		bind(CacheKeyGenerator.class).to(MethodSignatureGenerator.class);
		bind(RedisClient.class).toInstance(redisClient());
		bind(Cache.class).to(Redis.class);
		final DataSource dataSource = dataSource();
		bind(DataSource.class).toInstance(dataSource);
		bind(SessionFactory.class).toInstance(sessionFactory(dataSource));
		bind(SessionContext.class);
		final HibernateTransactionInterceptor transactionInterceptor = new HibernateTransactionInterceptor();
		requestInjection(transactionInterceptor);
		bindInterceptor(any(), annotatedWith(Transactional.class), transactionInterceptor);
	}

	private SessionFactory sessionFactory(DataSource dataSource) {
		return sessionFactoryBuilder()
				.addAnnotatedClass(PropertyEntity.class)
				.addAnnotatedClass(PropertyProvinceEntity.class)
				.withDataSource(dataSource)
				.build();
	}

	private RedisClient redisClient() {
		return RedisClient.create(format("redis://%s", CACHE_HOST.asString()));
	}

	private DataSource dataSource() {
		final Properties properties = new Properties();
		properties.put("dataSourceClassName", "com.mysql.cj.jdbc.MysqlDataSource");
		properties.put("dataSource.databaseName", "vrcc");
		properties.put("dataSource.user", "vrcc");
		properties.put("dataSource.password", "vrcc");
		properties.put("dataSource.portNumber", "3306");
		properties.put("dataSource.serverName", DB_HOST.asString());
		return new HikariDataSource(new HikariConfig(properties));
	}

}
