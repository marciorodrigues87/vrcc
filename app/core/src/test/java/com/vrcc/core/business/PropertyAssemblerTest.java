package com.vrcc.core.business;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.vrcc.domain.Property;
import com.vrcc.domain.Province;

@RunWith(MockitoJUnitRunner.class)
public class PropertyAssemblerTest {

	private static final String PROVINCE_NAME = "Z";

	private PropertyAssembler propertyAssembler;

	@Mock
	private Property property;

	@Mock
	private Province province;

	@Before
	public void before() {
		propertyAssembler = new PropertyAssembler();
	}

	@Test
	public void shouldAssemblyProperty() {
		when(province.getName()).thenReturn(PROVINCE_NAME);
		final Property assembled = propertyAssembler.assembly(property, asList(province));
		assertEquals(PROVINCE_NAME, assembled.getProvinces().iterator().next());
	}
}
