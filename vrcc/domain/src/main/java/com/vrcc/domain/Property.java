package com.vrcc.domain;

import static java.util.Collections.unmodifiableCollection;
import static java.util.stream.Collectors.toList;

import java.io.Serializable;
import java.util.Collection;

public class Property implements Serializable {

	private static final long serialVersionUID = 1L;

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

	public static Property full(long id, int x, int y, String title, long price, String description, int beds,
			int baths,
			long squareMeters, Collection<String> provinces) {
		return new Property(id, x, y, title, price, description, beds, baths, squareMeters, provinces);
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
		return unmodifiableCollection(provinces);
	}

	public Property addProvince(String province) {
		this.provinces.add(province);
		return this;
	}

	public Property addProvinces(Collection<String> province) {
		this.provinces.addAll(province);
		return this;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + baths;
		result = prime * result + beds;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + (int) (price ^ (price >>> 32));
		result = prime * result + ((provinces == null) ? 0 : provinces.hashCode());
		result = prime * result + (int) (squareMeters ^ (squareMeters >>> 32));
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Property other = (Property) obj;
		if (baths != other.baths)
			return false;
		if (beds != other.beds)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (price != other.price)
			return false;
		if (provinces == null) {
			if (other.provinces != null)
				return false;
		} else if (!provinces.equals(other.provinces))
			return false;
		if (squareMeters != other.squareMeters)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

}
