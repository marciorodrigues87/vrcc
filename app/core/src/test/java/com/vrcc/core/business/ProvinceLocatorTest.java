package com.vrcc.core.business;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.vrcc.domain.Boundary;
import com.vrcc.domain.Property;
import com.vrcc.infra.dao.ProvinceDAO;

@RunWith(MockitoJUnitRunner.class)
public class ProvinceLocatorTest {

	private static final int Y = 6;
	private static final int X = 1;

	@InjectMocks
	private ProvinceLocator provinceLocator;

	@Mock
	private ProvinceDAO provinceDAO;

	@Mock
	private Property property;

	@Test
	public void shouldLocateProperty() {

		when(property.getX()).thenReturn(X);
		when(property.getY()).thenReturn(Y);

		provinceLocator.locate(property);

		verify(provinceDAO).find(Boundary.at(X, Y));

	}
}
