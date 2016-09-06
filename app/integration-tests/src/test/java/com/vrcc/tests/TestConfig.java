package com.vrcc.tests;

import static java.lang.System.getProperty;

public enum TestConfig {

	APP_HOST("vrcc.tests.integration.host", "http://192.168.99.100"), 
	APP_PORT("vrcc.tests.integration.port", "8080");

	private final String key;
	private final String defaultValue;

	private TestConfig(String key, String defaultValue) {
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
