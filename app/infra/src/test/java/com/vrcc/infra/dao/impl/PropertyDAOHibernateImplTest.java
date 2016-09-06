package com.vrcc.infra.dao.impl;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collection;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.StatelessSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.vrcc.domain.Property;
import com.vrcc.domain.PropertyFilter;
import com.vrcc.infra.dao.impl.entity.PropertyEntity;
import com.vrcc.utils.hibernate.SessionContext;

@RunWith(MockitoJUnitRunner.class)
public class PropertyDAOHibernateImplTest {

	private static final long ID = 1;

	@InjectMocks
	private PropertyDAOHibernateImpl dao;

	@Mock
	private SessionContext ctx;

	@Mock
	private Property property;

	@Mock
	private Session session;

	@Mock
	private StatelessSession stateless;

	@Mock
	private Query query;

	@Mock
	private PropertyEntity propertyEntity;

	@Mock
	private PropertyFilter filter;

	@Test
	public void shouldAddProperty() {
		when(ctx.get()).thenReturn(session);
		final Property addedProperty = dao.add(property);
		assertNotNull(addedProperty);
		verify(session).persist(any(PropertyEntity.class));
	}

	@Test
	public void shouldGetProperty() {
		mockGetPropertyWith(propertyEntity);
		final Property property = dao.get(ID);
		assertEquals(this.property, property);
	}

	@Test
	public void shouldNotGetProperty() {
		mockGetPropertyWith(null);
		final Property property = dao.get(ID);
		assertNull(property);
	}

	@Test
	public void shouldFindProperties() {
		mockCreateQuery();
		when(query.setInteger("ax", 0)).thenReturn(query);
		when(query.setInteger("bx", 0)).thenReturn(query);
		when(query.setInteger("by", 0)).thenReturn(query);
		when(query.setInteger("ay", 0)).thenReturn(query);
		mockQueryReturn(propertyEntity, propertyEntity);
		final Collection<Property> properties = dao.find(filter);
		assertEquals(1, properties.size());
		assertEquals(property, properties.iterator().next());
	}

	private void mockCreateQuery() {
		when(ctx.stateless()).thenReturn(stateless);
		when(stateless.createQuery(anyString())).thenReturn(query);
	}

	private void mockGetPropertyWith(PropertyEntity propertyEntity) {
		mockCreateQuery();
		when(query.setLong("id", ID)).thenReturn(query);
		mockQueryReturn(propertyEntity);
	}

	private void mockQueryReturn(PropertyEntity... mocks) {
		final boolean exists = mocks != null && mocks.length > 0 && mocks[0] != null;
		when(query.list()).thenReturn(exists ? asList(mocks) : emptyList());
		if (exists) {
			for (PropertyEntity mock : mocks) {
				when(mock.toDomain()).thenReturn(property);
			}
		}
	}

}
