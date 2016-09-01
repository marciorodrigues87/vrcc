package com.vrcc.domain;

import static java.util.stream.Collectors.toList;

import java.util.Collection;

public class Property {

	private final long id;
	private final int x;
	private final int y;
	private final String title;
	private final long price;
	private final String description;
	private final int beds;
	private final int baths;
	private final long squareMeters;
	private final Collection<String> provinces;

	private Property(long id, int x, int y, String title, long price, String description, int beds, int baths,
			long squareMeters, Collection<String> provinces) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.title = title;
		this.price = price;
		this.description = description;
		this.beds = beds;
		this.baths = baths;
		this.squareMeters = squareMeters;
		this.provinces = provinces;
	}

	public static Property added(long id, Property added) {
		return new Property(id, added.x, added.y, added.title, added.price, added.description,
				added.beds, added.baths, added.squareMeters, added.provinces);
	}

	public static Property located(Property added, Collection<Province> provinces) {
		return new Property(added.id, added.x, added.y, added.title, added.price, added.description,
				added.beds, added.baths, added.squareMeters, provinces(provinces));
	}

	private static Collection<String> provinces(Collection<Province> provinces) {
		return provinces.stream().map(province -> province.getName()).collect(toList());
	}

	public static Property brandNew(int x, int y, String title, long price, String description, int beds, int baths,
			long squareMeters) {
		return new Property(0L, x, y, title, price, description, beds, baths, squareMeters, null);
	}

	public long getId() {
		return id;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public String getTitle() {
		return title;
	}

	public long getPrice() {
		return price;
	}

	public String getDescription() {
		return description;
	}

	public int getBeds() {
		return beds;
	}

	public int getBaths() {
		return baths;
	}

	public long getSquareMeters() {
		return squareMeters;
	}

	public Collection<String> getProvinces() {
		return provinces;
	}

}
