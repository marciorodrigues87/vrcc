package com.vrcc.infra.dao.impl.entity;

import static java.util.stream.Collectors.toList;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.vrcc.domain.Property;

@Entity
@Table(name = "property")
public class PropertyEntity {

	private long id;
	private int x;
	private int y;
	private String title;
	private long price;
	private String description;
	private int beds;
	private int baths;
	private long squareMeters;
	private Collection<PropertyProvinceEntity> provinces;

	public PropertyEntity() {
	}

	public PropertyEntity(Property property) {
		this.id = property.getId();
		this.x = property.getX();
		this.y = property.getY();
		this.title = property.getTitle();
		this.price = property.getPrice();
		this.description = property.getDescription();
		this.beds = property.getBeds();
		this.baths = property.getBaths();
		this.squareMeters = property.getSquareMeters();
		this.provinces = property.getProvinces().stream().map(s -> new PropertyProvinceEntity(this, s))
				.collect(toList());
	}

	public Property toDomain() {
		return Property.full(id, x, y, title, price, description, beds, baths, squareMeters, provinces(provinces));
	}

	private Collection<String> provinces(Collection<PropertyProvinceEntity> provinces) {
		return provinces.stream().map(p -> p.getName()).collect(toList());
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id")
	public long getId() {
		return id;
	}

	@Column(name = "x")
	public int getX() {
		return x;
	}

	@Column(name = "y")
	public int getY() {
		return y;
	}

	@Column(name = "title")
	public String getTitle() {
		return title;
	}

	@Column(name = "price")
	public long getPrice() {
		return price;
	}

	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	@Column(name = "beds")
	public int getBeds() {
		return beds;
	}

	@Column(name = "baths")
	public int getBaths() {
		return baths;
	}

	@Column(name = "square_meters")
	public long getSquareMeters() {
		return squareMeters;
	}

	@OneToMany(fetch = LAZY, cascade = ALL, mappedBy = "property")
	public Collection<PropertyProvinceEntity> getProvinces() {
		return provinces;
	}

	void setId(long id) {
		this.id = id;
	}

	void setX(int x) {
		this.x = x;
	}

	void setY(int y) {
		this.y = y;
	}

	void setTitle(String title) {
		this.title = title;
	}

	void setPrice(long price) {
		this.price = price;
	}

	void setDescription(String description) {
		this.description = description;
	}

	void setBeds(int beds) {
		this.beds = beds;
	}

	void setBaths(int baths) {
		this.baths = baths;
	}

	void setSquareMeters(long squareMeters) {
		this.squareMeters = squareMeters;
	}

	void setProvinces(Collection<PropertyProvinceEntity> provinces) {
		this.provinces = provinces;
	}

}
