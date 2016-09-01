package com.vrcc.api.servlet;

import com.vrcc.domain.Properties;
import com.vrcc.domain.Property;
import com.vrcc.domain.PropertyFilter;

public interface PropertiesResource {

	Property add(Property request);

	Property get(long id);

	Properties find(PropertyFilter filter);
	
}
