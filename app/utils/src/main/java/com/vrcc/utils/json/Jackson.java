package com.vrcc.utils.json;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Singleton
public class Jackson implements JsonProvider {

	private final ObjectMapper mapper;

	@Inject
	public Jackson(ObjectMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public String to(Object object) {
		try {
			return mapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public <K, V> Map<K, V> from(InputStream json, Class<K> key, Class<V> value) {
		try {
			return mapper.readValue(json, mapper.getTypeFactory().constructMapLikeType(HashMap.class, key, value));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
