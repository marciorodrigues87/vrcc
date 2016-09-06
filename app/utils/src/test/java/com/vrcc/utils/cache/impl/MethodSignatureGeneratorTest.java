package com.vrcc.utils.cache.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class MethodSignatureGeneratorTest {

	private MethodSignatureGenerator generator;

	@Before
	public void before() {
		generator = new MethodSignatureGenerator();
	}

	@Test
	public void shouldGenerateKey() throws NoSuchMethodException, SecurityException {
		final String key = generator.keyFor(
				this.getClass().getMethod("keyMethod", String.class, long.class),
				new Object[] { "XXX", 123L });
		assertEquals("MethodSignatureGeneratorTest/keyMethod/String/String/long/XXX_123", key);
	}

	public String keyMethod(String a, long b) {
		return null;
	}

}
