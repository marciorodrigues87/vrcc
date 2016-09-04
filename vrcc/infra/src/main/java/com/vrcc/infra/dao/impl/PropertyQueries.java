package com.vrcc.infra.dao.impl;

public final class PropertyQueries {

	static final String SQL_INSERT_PROPERTY = "INSERT INTO property (x, y, title, price, description, beds, baths, squareMeters) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	static final String SQL_SELECT_ONE = "SELECT p.id, p.x, p.y, p.title, p.price, p.description, p.beds, p.baths, p.squareMeters, pp.name FROM property p JOIN property_province pp ON p.id = pp.property_id WHERE p.id = ?";
	static final String SQL_SELECT_FILTER = "SELECT p.id, p.x, p.y, p.title, p.price, p.description, p.beds, p.baths, p.squareMeters, pp.name FROM property p JOIN property_province pp ON p.id = pp.property_id WHERE p.x >= ? AND p.x <= ? AND p.y >= ? AND p.y <= ?";
	static final String SQL_INSERT_PROVINCES = "INSERT INTO property_province (property_id, name) VALUES (?, ?)";

	private PropertyQueries() {
	}
}
