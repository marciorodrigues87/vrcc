package com.vrcc.core.dao;

import java.util.Collection;

import com.vrcc.domain.Property;
import com.vrcc.domain.PropertyFilter;

public interface PropertyDAO {

	Property add(Property property);

	Property get(long id);

	Collection<Property> find(PropertyFilter filter);

}
