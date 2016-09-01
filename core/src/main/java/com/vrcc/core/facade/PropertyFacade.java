package com.vrcc.core.facade;

import com.vrcc.domain.Properties;
import com.vrcc.domain.Property;
import com.vrcc.domain.PropertyFilter;

public interface PropertyFacade {

	Property add(Property request);

	Property get(long id);

	Properties find(PropertyFilter filter);
}