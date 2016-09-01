package com.vrcc.core.business;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.vrcc.core.dao.ProvinceDAO;
import com.vrcc.domain.Property;
import com.vrcc.domain.Province;

@Singleton
public class ProvinceLocator {

	private final ProvinceDAO provinceDAO;

	@Inject
	public ProvinceLocator(ProvinceDAO provinceDAO) {
		this.provinceDAO = provinceDAO;
	}

	public Collection<Province> locate(Property property) {
		return null;
	}

}
