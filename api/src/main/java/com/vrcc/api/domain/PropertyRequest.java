package com.vrcc.api.domain;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vrcc.api.domain.validation.Bath;
import com.vrcc.api.domain.validation.Bed;
import com.vrcc.api.domain.validation.Price;
import com.vrcc.api.domain.validation.SquareMeter;
import com.vrcc.api.domain.validation.X;
import com.vrcc.api.domain.validation.Y;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PropertyRequest {

	private String x;
	private String y;
	private String title;
	private String price;
	private String description;
	private String beds;
	private String baths;
	private String squareMeters;

	public PropertyRequest() {
	}

	@X
	public String getX() {
		return x;
	}

	@Y
	public String getY() {
		return y;
	}

	@NotBlank
	public String getTitle() {
		return title;
	}

	@Price
	public String getPrice() {
		return price;
	}

	@NotBlank
	public String getDescription() {
		return description;
	}

	@Bed
	public String getBeds() {
		return beds;
	}

	@Bath
	public String getBaths() {
		return baths;
	}

	@SquareMeter
	public String getSquareMeters() {
		return squareMeters;
	}

}
