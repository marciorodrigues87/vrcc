package com.vrcc.api.config;

import static java.lang.System.getProperty;

public enum Config {

	CACHE_HOST("cache.host", "cache"), 
	DB_HOST("cache.host", "db");

	private final String key;
	private final String defaultValue;

	private Config(String key, String defaultValue) {
		this.key = key;
		this.defaultValue = defaultValue;
	}

	public String asString() {
		return getProperty(key, defaultValue);
	}

	public int asInt() {
		return Integer.valueOf(asString());
	}

	public boolean asBoolean() {
		return Boolean.valueOf(asString());
	}

}
