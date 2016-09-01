package com.vrcc.api.converter;

import java.util.Collection;

import javax.inject.Inject;

import com.vrcc.api.domain.PropertiesResponse;
import com.vrcc.domain.Property;

public class PropertiesResponseConverter {

	private final PropertyResponseConverter propertyConverter;

	@Inject
	public PropertiesResponseConverter(PropertyResponseConverter propertyConverter) {
		this.propertyConverter = propertyConverter;
	}

	public PropertiesResponse convert(Collection<Property> properties) {
		return new PropertiesResponse(properties.size(), propertyConverter.convert(properties));
	}

}
