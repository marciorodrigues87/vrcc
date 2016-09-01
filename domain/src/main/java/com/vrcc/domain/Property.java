package com.vrcc.domain;

import javax.validation.constraints.Null;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.vrcc.domain.validation.Bath;
import com.vrcc.domain.validation.Bed;
import com.vrcc.domain.validation.SquareMeter;
import com.vrcc.domain.validation.X;
import com.vrcc.domain.validation.Y;

@JsonIgnoreProperties(ignoreUnknown = true)
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
	private final String[] provinces;

	@JsonCreator
	public Property(
			@JsonProperty("id") long id, 
			@JsonProperty("x") int x, 
			@JsonProperty("y") int y, 
			@JsonProperty("title") String title, 
			@JsonProperty("price") long price,
			@JsonProperty("description") String description, 
			@JsonProperty("beds") int beds, 
			@JsonProperty("baths") int baths, 
			@JsonProperty("squareMeters") long squareMeters,
			@JsonProperty("provinces") String[] provinces
		) {
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

	public Property(long id, Property added) {
		this(id, added.x, added.y, added.title, added.price, added.description,
				added.beds, added.baths, added.squareMeters, added.provinces);
	}

	@Null
	public long getId() {
		return id;
	}

	@X
	public int getX() {
		return x;
	}

	@Y
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

	@Bed
	public int getBeds() {
		return beds;
	}

	@Bath
	public int getBaths() {
		return baths;
	}

	@SquareMeter
	public long getSquareMeters() {
		return squareMeters;
	}

	public String[] getProvinces() {
		return provinces;
	}

}
