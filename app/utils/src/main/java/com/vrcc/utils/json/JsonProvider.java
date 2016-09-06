package com.vrcc.utils.json;

import java.io.InputStream;
import java.util.Map;

public interface JsonProvider {

	String to(Object object);

	<K, V> Map<K, V> from(InputStream json, Class<K> key, Class<V> value);

}
