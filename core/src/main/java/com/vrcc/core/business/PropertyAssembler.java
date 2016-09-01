package com.vrcc.core.business;

import java.util.Collection;

import javax.inject.Singleton;

import com.vrcc.domain.Property;
import com.vrcc.domain.Province;

@Singleton
public class PropertyAssembler {

	public Property assembly(Property property, Collection<Province> provinces) {
		return Property.located(property, provinces);
	}

}
