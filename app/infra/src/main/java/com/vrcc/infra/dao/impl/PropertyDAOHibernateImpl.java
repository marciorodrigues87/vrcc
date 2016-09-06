package com.vrcc.infra.dao.impl;

import static com.vrcc.infra.dao.impl.PropertyHQLQueries.HQL_SELECT_FILTER;
import static com.vrcc.infra.dao.impl.PropertyHQLQueries.HQL_SELECT_ONE;
import static java.util.stream.Collectors.toSet;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.vrcc.domain.Property;
import com.vrcc.domain.PropertyFilter;
import com.vrcc.infra.dao.PropertyDAO;
import com.vrcc.infra.dao.impl.entity.PropertyEntity;
import com.vrcc.utils.cache.Cached;
import com.vrcc.utils.cache.Expires;
import com.vrcc.utils.hibernate.SessionContext;
import com.vrcc.utils.hibernate.Transactional;

@Singleton
public class PropertyDAOHibernateImpl implements PropertyDAO {

	private final SessionContext session;

	@Inject
	public PropertyDAOHibernateImpl(SessionContext session) {
		this.session = session;
	}

	@Override
	@Transactional
	@Expires(methodName = "find")
	public Property add(Property property) {
		final PropertyEntity propertyEntity = propertyEntity(property);
		session.get().persist(propertyEntity);
		return property(propertyEntity);
	}

	private PropertyEntity propertyEntity(Property property) {
		return new PropertyEntity(property);
	}

	@Override
	@Cached(ttl = 60)
	public Property get(long id) {
		@SuppressWarnings("unchecked")
		final List<PropertyEntity> list = session.stateless()
				.createQuery(HQL_SELECT_ONE)
				.setLong("id", id)
				.list();
		return property(list.stream().findFirst().orElse(null));
	}

	private Property property(PropertyEntity property) {
		if (property == null) {
			return null;
		}
		return property.toDomain();
	}

	@Override
	@Cached(ttl = 60)
	@SuppressWarnings("unchecked")
	public Collection<Property> find(PropertyFilter filter) {
		final List<PropertyEntity> list = session.stateless()
				.createQuery(HQL_SELECT_FILTER)
				.setInteger("ax", filter.getAx())
				.setInteger("bx", filter.getBx())
				.setInteger("by", filter.getBy())
				.setInteger("ay", filter.getAy())
				.list();
		return list.stream().map(e -> property(e)).collect(toSet());
	}

}
