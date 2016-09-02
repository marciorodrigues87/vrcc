package com.vrcc.domain;

public class Province {

	private final String name;
	private final Boundaries boundaries;

	Province() {
		this(null, null);
	}

	private Province(String name, Boundaries boundaries) {
		this.name = name;
		this.boundaries = boundaries;
	}

	public static Province full(String name, Boundaries boundaries) {
		return new Province(name, boundaries);
	}

	public String getName() {
		return name;
	}

	public Boundaries getBoundaries() {
		return boundaries;
	}

}
