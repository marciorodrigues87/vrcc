package com.vrcc.core.facade.impl;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.vrcc.core.business.PropertyAssembler;
import com.vrcc.core.business.ProvinceLocator;
import com.vrcc.domain.Property;
import com.vrcc.domain.Province;
import com.vrcc.infra.dao.PropertyDAO;

@RunWith(MockitoJUnitRunner.class)
public class SimplePropertyFacadeTest {

	private static final int ID = 123;

	private SimplePropertyFacade facade;

	@Mock
	private PropertyDAO propertyDAO;

	@Mock
	private ProvinceLocator provinceLocator;

	@Mock
	private PropertyAssembler propertyAssembler;

	@Mock
	private Property property;

	@Mock
	private Province province;

	@Before
	public void before() {
		facade = new SimplePropertyFacade(propertyDAO, provinceLocator, propertyAssembler);
	}

	@Test
	public void shouldAddProperty() {

		final List<Province> provinces = asList(province);
		when(provinceLocator.locate(property)).thenReturn(provinces);
		when(propertyAssembler.assembly(property, provinces)).thenReturn(property);

		facade.add(property);

		verify(provinceLocator).locate(property);
		verify(propertyAssembler).assembly(property, provinces);
		verify(propertyDAO).add(property);
	}

	@Test
	public void shouldGetProperty() {

		final Property property = facade.get(ID);

		assertNull(property);

		verify(propertyDAO).get(ID);

	}

}
