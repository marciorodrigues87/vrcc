package com.vrcc.infra.dao.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.vrcc.domain.Property;
import com.vrcc.domain.PropertyFilter;

@RunWith(MockitoJUnitRunner.class)
public class PropertyDAOMemoryImplTest {

	private static final int ID = 1;

	private PropertyDAOMemoryImpl dao;

	@Mock
	private Property property;

	@Mock
	private PropertyFilter filter;

	@Before
	public void before() {
		dao = new PropertyDAOMemoryImpl();
	}

	@Test
	public void shouldAddProperty() {
		final Property added = dao.add(property);
		assertEquals(added.getId(), ID);
	}

	@Test
	public void shouldGetProperty() {
		final Property property = dao.get(ID);
		assertNull(property);
	}

	@Test
	public void shouldFindProperties() {

		final Property property1 = propertyIn(1, 2);
		dao.add(property1);

		final Property property2 = propertyIn(12, 12);
		dao.add(property2);

		when(filter.getAx()).thenReturn(1);
		when(filter.getAy()).thenReturn(2);
		when(filter.getBx()).thenReturn(5);
		when(filter.getBy()).thenReturn(5);

		final Collection<Property> properties = dao.find(filter);

		assertEquals(1, properties.size());
		assertEquals(ID, properties.iterator().next().getId());

	}

	private Property propertyIn(int x, int y) {
		return Property.brandNew(x, y, null, 0, null, 0, 0, 0);
	}

}
