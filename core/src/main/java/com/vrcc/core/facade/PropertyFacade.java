package com.vrcc.core.facade;

import java.util.Collection;

import com.vrcc.domain.Property;
import com.vrcc.domain.PropertyFilter;

public interface PropertyFacade {

	Property add(Property property);

	Property get(long id);

	Collection<Property> find(PropertyFilter filter);
}