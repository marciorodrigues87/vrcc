package com.vrcc.api.converter;

import static java.lang.Long.parseLong;
import static java.util.stream.Collectors.toList;

import java.util.Collection;

import com.vrcc.api.domain.PropertyResponse;
import com.vrcc.domain.Property;

public class PropertyResponseConverter {

	public PropertyResponse convert(Property property) {
		if (property == null) {
			return null;
		}
		return new PropertyResponse(property.getId(), property.getX(), property.getY(), property.getTitle(),
				property.getPrice(), property.getDescription(), property.getBeds(), property.getBaths(),
				property.getSquareMeters(), property.getProvinces());
	}

	public Collection<PropertyResponse> convert(Collection<Property> properties) {
		return properties.stream().map(p -> convert(p)).collect(toList());
	}

	public long convert(String id) {
		return parseLong(id);
	}

}
