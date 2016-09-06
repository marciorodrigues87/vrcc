package com.vrcc.api.config;

import static com.vrcc.api.config.Config.CACHE_HOST;
import static java.lang.System.getProperties;
import static java.lang.System.setProperty;
import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Map;

import org.junit.Test;

public class ConfigTest {

	@Test
	public void shouldGetEnvValueIfExists() throws Exception {
		setenv(CACHE_HOST.key(), "x");
		assertEquals("x", CACHE_HOST.asString());
	}

	@Test
	public void shouldGetSystemPropertyIfExists() throws Exception {
		setenv(CACHE_HOST.key(), null);
		setProperty(CACHE_HOST.key(), "y");
		assertEquals("y", CACHE_HOST.asString());
	}

	@Test
	public void shouldGetDefaultProperty() throws Exception {
		setenv(CACHE_HOST.key(), null);
		getProperties().remove(CACHE_HOST.key());
		assertEquals("cache", CACHE_HOST.asString());
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void setenv(String key, String value) throws Exception {
		final Class[] classes = Collections.class.getDeclaredClasses();
		final Map<String, String> env = System.getenv();
		for (Class cl : classes) {
			if ("java.util.Collections$UnmodifiableMap".equals(cl.getName())) {
				final Field field = cl.getDeclaredField("m");
				field.setAccessible(true);
				final Object obj = field.get(env);
				final Map<String, String> map = (Map<String, String>) obj;
				if (value == null) {
					map.remove(key);
				} else {
					map.put(key, value);
				}
			}
		}
	}

}
