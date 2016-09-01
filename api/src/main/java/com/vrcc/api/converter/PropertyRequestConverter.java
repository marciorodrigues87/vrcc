package com.vrcc.api.converter;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

import com.vrcc.api.domain.PropertyRequest;
import com.vrcc.domain.Property;

public class PropertyRequestConverter {

	public Property convert(PropertyRequest request) {
		return Property.brandNew(parseInt(request.getX()), parseInt(request.getY()), request.getTitle(),
				parseLong(request.getPrice()),
				request.getDescription(), parseInt(request.getBeds()), parseInt(request.getBaths()),
				parseLong(request.getSquareMeters()));
	}

}
