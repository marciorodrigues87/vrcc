package com.vrcc.infra.dao.impl;

import static com.vrcc.infra.dao.impl.PropertyHQLQueries.HQL_SELECT_FILTER;
import static com.vrcc.infra.dao.impl.PropertyHQLQueries.HQL_SELECT_ONE;
import static java.util.stream.Collectors.toSet;

import java.util.Collection;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;

import com.vrcc.domain.Property;
import com.vrcc.domain.PropertyFilter;
import com.vrcc.infra.dao.PropertyDAO;
import com.vrcc.infra.dao.impl.entity.PropertyEntity;
import com.vrcc.utils.cache.Cached;

@Singleton
public class PropertyDAOHibernateImpl implements PropertyDAO {

	private final SessionFactory sf;

	@Inject
	public PropertyDAOHibernateImpl(SessionFactory sf) {
		this.sf = sf;
	}

	@Override
	public Property add(Property property) {
		final PropertyEntity propertyEntity = propertyEntity(property);
		Transaction tx = null;
		try (final Session session = session()) {
			tx = session.beginTransaction();
			session.persist(propertyEntity);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		}
		return property(propertyEntity);
	}

	private PropertyEntity propertyEntity(Property property) {
		return new PropertyEntity(property);
	}

	@Override
	@Cached(ttl = 60)
	public Property get(long id) {
		PropertyEntity property = null;
		try (final StatelessSession session = stateless()) {
			@SuppressWarnings("unchecked")
			final List<PropertyEntity> list = session
					.createQuery(HQL_SELECT_ONE)
					.setLong("id", id)
					.list();
			property = (PropertyEntity) (list.isEmpty() ? null : list.iterator().next());
		}
		return property(property);
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
		final List<PropertyEntity> list;
		try (final StatelessSession session = stateless()) {
			list = session.createQuery(HQL_SELECT_FILTER)
					.setInteger("ax", filter.getAx())
					.setInteger("bx", filter.getBx())
					.setInteger("by", filter.getBy())
					.setInteger("ay", filter.getAy())
					.list();
		}
		return list.stream().map(e -> property(e)).collect(toSet());
	}

	private StatelessSession stateless() {
		return sf.openStatelessSession();
	}

	private Session session() {
		return sf.openSession();
	}

}