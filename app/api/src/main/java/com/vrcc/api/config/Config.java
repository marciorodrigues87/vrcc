package com.vrcc.api.config;

import static java.lang.System.getProperty;
import static java.lang.System.getenv;

public enum Config {

	CACHE_HOST("vrcc.cache.host", "cache"), 
	DB_HOST("vrcc.db.host", "db");

	private final String key;
	private final String defaultValue;

	private Config(String key, String defaultValue) {
		this.key = key;
		this.defaultValue = defaultValue;
	}

	public String asString() {
		final String env = getenv(key);
		if (env != null) {
			return env;
		}
		return getProperty(key, defaultValue);
	}

	public int asInt() {
		return Integer.valueOf(asString());
	}

	public boolean asBoolean() {
		return Boolean.valueOf(asString());
	}

}
