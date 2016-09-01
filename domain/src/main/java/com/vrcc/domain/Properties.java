package com.vrcc.domain;

public class Properties {

	private final long foundProperties;
	private final Property[] properties;

	public Properties(long foundProperties, Property[] properties) {
		this.foundProperties = foundProperties;
		this.properties = properties;
	}

	public long getFoundProperties() {
		return foundProperties;
	}

	public Property[] getProperties() {
		return properties;
	}

}
