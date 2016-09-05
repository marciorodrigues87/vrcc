package com.vrcc.utils.hibernate;

import java.util.ArrayList;
import java.util.Collection;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.SessionFactoryBuilder;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

public class HibernateSessionFactoryBuilder {

	private DataSource dataSource;
	private Collection<Class<?>> annotatedClasses = new ArrayList<>();

	private HibernateSessionFactoryBuilder() {
	}

	public static HibernateSessionFactoryBuilder sessionFactoryBuilder() {
		return new HibernateSessionFactoryBuilder();
	}

	public HibernateSessionFactoryBuilder withDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		return this;
	}

	public HibernateSessionFactoryBuilder addAnnotatedClass(Class<?> clazz) {
		this.annotatedClasses.add(clazz);
		return this;
	}

	private SessionFactory sessionFactory() {
		return sessionFactoryBuilder(metadata(standardServiceRegistry())).build();
	}

	private static SessionFactoryBuilder sessionFactoryBuilder(final Metadata metadata) {
		return metadata.getSessionFactoryBuilder();
	}

	private Metadata metadata(final StandardServiceRegistry standardRegistry) {
		final MetadataSources metadataSources = new MetadataSources(standardRegistry);
		for (Class<?> clazz : annotatedClasses) {
			metadataSources.addAnnotatedClass(clazz);
		}
		return metadataSources
				.getMetadataBuilder()
				.applyImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl.INSTANCE)
				.build();
	}

	private StandardServiceRegistry standardServiceRegistry() {
		final StandardServiceRegistryBuilder standardServiceRegistryBuilder = new StandardServiceRegistryBuilder();
		return standardServiceRegistryBuilder
				.applySetting(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect")
				.applySetting(Environment.DATASOURCE, dataSource)
				.applySetting(Environment.SHOW_SQL, "true")
				.applySetting(Environment.FORMAT_SQL, "true")
				.applySetting(Environment.USE_SQL_COMMENTS, "true")
				.build();
	}

	public SessionFactory build() {
		return sessionFactory();
	}

}
