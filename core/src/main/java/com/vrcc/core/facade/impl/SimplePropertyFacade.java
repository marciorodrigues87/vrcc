package com.vrcc.core.facade.impl;

import javax.inject.Inject;

import com.vrcc.core.dao.PropertyDAO;
import com.vrcc.core.facade.PropertyFacade;
import com.vrcc.domain.Properties;
import com.vrcc.domain.Property;
import com.vrcc.domain.PropertyFilter;

public class SimplePropertyFacade implements PropertyFacade {

	private final PropertyDAO dao;

	@Inject
	public SimplePropertyFacade(PropertyDAO dao) {
		this.dao = dao;
	}

	@Override
	public Property add(Property request) {
		return dao.add(request);
	}

	@Override
	public Property get(long id) {
		return dao.get(id);
	}

	@Override
	public Properties find(PropertyFilter filter) {
		final Property[] properties = dao.find(filter.getAx(), filter.getAy(),
				filter.getBx(), filter.getBy());
		return new Properties(properties.length, properties);
	}

}
