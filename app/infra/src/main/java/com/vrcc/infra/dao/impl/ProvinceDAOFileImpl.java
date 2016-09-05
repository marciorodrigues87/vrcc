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
import com.vrcc.utils.math.Point;
import com.vrcc.utils.math.Rectangle;

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
				.filter(e -> contains(boundary, e.getValue()))
				.map(e -> assembly(e))
				.collect(toList());
	}

	private Province assembly(Entry<String, Province> e) {
		return Province.full(e.getKey(), e.getValue().getBoundaries());
	}

	public boolean contains(Boundary boundary, Province province) {
		return rectangle(province).contains(point(boundary));
	}

	private Point point(Boundary boundary) {
		return Point.at(boundary.getX(), boundary.getY());
	}

	private Rectangle rectangle(Province province) {
		final Boundaries boundaries = province.getBoundaries();
		return Rectangle.at(upperLeft(boundaries), bottomRight(boundaries));
	}

	private Point bottomRight(final Boundaries boundaries) {
		return Point.at(boundaries.getBottomRight().getX(), boundaries.getBottomRight().getY());
	}

	private Point upperLeft(final Boundaries boundaries) {
		return Point.at(boundaries.getUpperLeft().getX(), boundaries.getUpperLeft().getY());
	}

}
