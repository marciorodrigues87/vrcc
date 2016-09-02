package com.vrcc.core.facade.impl;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.vrcc.core.business.PropertyAssembler;
import com.vrcc.core.business.ProvinceLocator;
import com.vrcc.core.facade.PropertyFacade;
import com.vrcc.domain.Property;
import com.vrcc.domain.PropertyFilter;
import com.vrcc.domain.Province;
import com.vrcc.infra.dao.PropertyDAO;

@Singleton
public class SimplePropertyFacade implements PropertyFacade {

	private final PropertyDAO propertyDAO;
	private final ProvinceLocator provinceLocator;
	private final PropertyAssembler propertyAssembler;

	@Inject
	public SimplePropertyFacade(PropertyDAO propertyDAO, ProvinceLocator provinceLocator,
			PropertyAssembler propertyAssembler) {
		this.propertyDAO = propertyDAO;
		this.provinceLocator = provinceLocator;
		this.propertyAssembler = propertyAssembler;
	}

	@Override
	public Property add(Property property) {
		final Collection<Province> provinces = provinceLocator.locate(property);
		final Property completeProperty = propertyAssembler.assembly(property, provinces);
		return propertyDAO.add(completeProperty);
	}

	@Override
	public Property get(long id) {
		return propertyDAO.get(id);
	}

	@Override
	public Collection<Property> find(PropertyFilter filter) {
		return propertyDAO.find(filter);
	}

}
