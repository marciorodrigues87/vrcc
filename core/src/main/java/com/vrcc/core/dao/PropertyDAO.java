package com.vrcc.core.dao;

import com.vrcc.domain.Property;

public interface PropertyDAO {

	Property add(Property property);

	Property get(long id);

	Property[] find(int ax, int ay, int bx, int by);
	
}
