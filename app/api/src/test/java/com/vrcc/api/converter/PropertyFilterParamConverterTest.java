package com.vrcc.api.converter;

import static java.lang.Integer.parseInt;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.vrcc.api.domain.PropertyFilterParam;
import com.vrcc.domain.PropertyFilter;

@RunWith(MockitoJUnitRunner.class)
public class PropertyFilterParamConverterTest {

	private static final String BY = "4";
	private static final String BX = "3";
	private static final String AY = "2";
	private static final String AX = "1";

	@InjectMocks
	private PropertyFilterParamConverter converter;

	@Mock
	private PropertyFilterParam filter;

	@Test
	public void shouldConvertFilter() {
		when(filter.getAx()).thenReturn(AX);
		when(filter.getAy()).thenReturn(AY);
		when(filter.getBx()).thenReturn(BX);
		when(filter.getBy()).thenReturn(BY);

		final PropertyFilter propertyFilter = converter.convert(filter);

		assertEquals(parseInt(AX), propertyFilter.getAx());
		assertEquals(parseInt(AY), propertyFilter.getAy());
		assertEquals(parseInt(BX), propertyFilter.getBx());
		assertEquals(parseInt(BY), propertyFilter.getBy());
	}

}
