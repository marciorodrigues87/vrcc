package com.vrcc.infra.dao.impl;

import static java.util.Collections.synchronizedMap;
import static java.util.stream.Collectors.toList;

import java.io.InputStream;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.vrcc.domain.Boundaries;
import com.vrcc.domain.Boundary;
import com.vrcc.domain.Province;
import com.vrcc.infra.dao.ProvinceDAO;
import com.vrcc.utils.json.JsonProvider;

@Singleton
public class ProvinceDAOFileImpl implements ProvinceDAO {

	private final Map<String, Province> provinces;

	@Inject
	public ProvinceDAOFileImpl(JsonProvider json) {
		this.provinces = synchronizedMap(json.from(provincesStream(), String.class, Province.class));
	}

	private InputStream provincesStream() {
		return getClass().getClassLoader().getResourceAsStream("provinces.json");
	}

	public Collection<Province> find(Boundary boundary) {
		return provinces.entrySet().stream()
				.filter(e -> contains(boundary, e.getValue().getBoundaries()))
				.map(e -> assembly(e))
				.collect(toList());
	}

	private Province assembly(Entry<String, Province> e) {
		return Province.full(e.getKey(), e.getValue().getBoundaries());
	}

	public boolean contains(Boundary boundary, Boundaries boundaries) {
		return boundary.getX() >= boundaries.getUpperLeft().getX()
				&& boundary.getX() <= boundaries.getBottomRight().getX()
				&& boundary.getY() <= boundaries.getUpperLeft().getY()
				&& boundary.getY() >= boundaries.getBottomRight().getY();
	}

}
