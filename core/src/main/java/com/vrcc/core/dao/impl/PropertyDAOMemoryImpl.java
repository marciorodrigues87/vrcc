package com.vrcc.core.dao.impl;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import javax.inject.Singleton;

import com.vrcc.core.dao.PropertyDAO;
import com.vrcc.domain.Property;

@Singleton
public class PropertyDAOMemoryImpl implements PropertyDAO {

	private final AtomicLong sequence = new AtomicLong(0);
	private final Map<Long, Property> database = new ConcurrentHashMap<>();

	@Override
	public Property add(Property property) {
		final long id = sequence.incrementAndGet();
		final Property added = new Property(id, property);
		database.put(id, added);
		return added;
	}

	@Override
	public Property get(long id) {
		return database.get(id);
	}

	@Override
	public Property[] find(int ax, int ay, int bx, int by) {
		final List<Property> properties = database.values()
				.stream()
				.filter(p -> (p.getX() >= ax && p.getX() <= bx
						&&
						p.getY() >= ay && p.getY() <= by))
				.collect(toList());
		return properties.toArray(new Property[properties.size()]);
	}

}
