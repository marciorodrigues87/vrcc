package com.vrcc.api.converter;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.vrcc.api.domain.PropertiesResponse;
import com.vrcc.domain.Property;

@RunWith(MockitoJUnitRunner.class)
public class PropertiesResponseConverterTest {

	@InjectMocks
	private PropertiesResponseConverter converter;

	@Mock
	private PropertyResponseConverter propertyConverter;

	@Mock
	private Property property;

	@Test
	public void shouldConvertProperties() {
		final List<Property> properties = asList(property, property);
		final PropertiesResponse response = converter.convert(properties);
		assertEquals(2, response.getFoundProperties());
		verify(propertyConverter, times(1)).convert(properties);
	}

}
