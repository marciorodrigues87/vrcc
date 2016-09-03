package com.vrcc.api.converter;

import static java.lang.Integer.parseInt;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.vrcc.api.domain.PropertyRequest;
import com.vrcc.domain.Property;

@RunWith(MockitoJUnitRunner.class)
public class PropertyRequestConverterTest {

	private static final String TITLE = "Y";
	private static final String SQR_METERS = "100";
	private static final String PRICE = "1000";
	private static final String DESCRIPTION = "X";
	private static final String BEDS = "1";
	private static final String BATHS = "1";
	private static final String X = "1";
	private static final String Y = "5";

	private PropertyRequestConverter converter;

	@Mock
	private PropertyRequest request;

	@Before
	public void before() {
		converter = new PropertyRequestConverter();
	}

	@Test
	public void shouldConvertProperty() {

		when(request.getBeds()).thenReturn(BEDS);
		when(request.getBaths()).thenReturn(BATHS);
		when(request.getDescription()).thenReturn(DESCRIPTION);
		when(request.getPrice()).thenReturn(PRICE);
		when(request.getSquareMeters()).thenReturn(SQR_METERS);
		when(request.getTitle()).thenReturn(TITLE);
		when(request.getY()).thenReturn(Y);
		when(request.getX()).thenReturn(X);

		final Property property = converter.convert(request);

		assertEquals(parseInt(BEDS), property.getBeds());
		assertEquals(parseInt(BATHS), property.getBaths());
		assertEquals(DESCRIPTION, property.getDescription());
		assertEquals(parseInt(PRICE), property.getPrice());
		assertEquals(parseInt(SQR_METERS), property.getSquareMeters());
		assertEquals(TITLE, property.getTitle());
		assertEquals(parseInt(Y), property.getY());
		assertEquals(parseInt(X), property.getX());
	}

}
