package com.vrcc.api.converter;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.vrcc.api.domain.PropertyResponse;
import com.vrcc.domain.Property;

@RunWith(MockitoJUnitRunner.class)
public class PropertyResponseConverterTest {

	private PropertyResponseConverter converter;

	@Mock
	private Property property;

	@Before
	public void before() {
		converter = new PropertyResponseConverter();
	}

	@Test
	public void shouldConvertProperty() {

		final Collection<PropertyResponse> properties = converter.convert(asList(property, null));

		assertEquals(2, properties.size());
	}

}
