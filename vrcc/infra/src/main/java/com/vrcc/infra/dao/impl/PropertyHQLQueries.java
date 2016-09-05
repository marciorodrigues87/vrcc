package com.vrcc.infra.dao.impl;

public class PropertyHQLQueries {

	static final String HQL_SELECT_ONE = "FROM PropertyEntity p INNER JOIN FETCH p.provinces WHERE p.id = :id";
	static final String HQL_SELECT_FILTER = "FROM PropertyEntity p INNER JOIN FETCH p.provinces WHERE p.x >= :ax AND p.x <= :bx AND p.y >= :by AND p.y <= :ay";

	private PropertyHQLQueries() {
	}

}
