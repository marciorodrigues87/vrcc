package com.vrcc.utils.json;

import java.io.InputStream;
import java.util.Map;

public interface JsonProvider {

	String to(Object object);

	<T> T from(String json, Class<T> clazz);

	<T> T from(InputStream json, Class<T> clazz);

	<K, V> Map<K, V> from(InputStream json, Class<K> key, Class<V> value);

}
