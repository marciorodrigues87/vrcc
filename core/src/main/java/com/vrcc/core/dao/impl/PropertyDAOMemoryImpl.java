package com.vrcc.core.dao.impl;

import static java.util.stream.Collectors.toList;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import javax.inject.Singleton;

import com.vrcc.core.dao.PropertyDAO;
import com.vrcc.domain.Property;
import com.vrcc.domain.PropertyFilter;

@Singleton
public class PropertyDAOMemoryImpl implements PropertyDAO {

	private final AtomicLong sequence = new AtomicLong(0);
	private final Map<Long, Property> database = new ConcurrentHashMap<>();

	@Override
	public Property add(Property property) {
		final long id = sequence.incrementAndGet();
		final Property added = Property.added(id, property);
		database.put(id, added);
		return added;
	}

	@Override
	public Property get(long id) {
		return database.get(id);
	}

	@Override
	public Collection<Property> find(PropertyFilter filter) {
		return database.values()
				.stream()
				.filter(property -> accept(property, filter))
				.collect(toList());
	}

	private boolean accept(Property property, PropertyFilter filter) {
		return property.getX() >= filter.getAx() && property.getX() <= filter.getBx()
				&& property.getY() >= filter.getAy() && property.getY() <= filter.getBy();
	}

}
