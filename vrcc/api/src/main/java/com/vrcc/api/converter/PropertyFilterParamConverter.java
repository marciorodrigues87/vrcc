package com.vrcc.api.converter;

import static java.lang.Integer.parseInt;

import com.vrcc.api.domain.PropertyFilterParam;
import com.vrcc.domain.PropertyFilter;

public class PropertyFilterParamConverter {

	public PropertyFilter convert(PropertyFilterParam filter) {
		return PropertyFilter.full(parseInt(filter.getAx()), parseInt(filter.getAy()), parseInt(filter.getBx()),
				parseInt(filter.getBy()));
	}

}
