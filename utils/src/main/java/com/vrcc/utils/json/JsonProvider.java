package com.vrcc.utils.json;

import java.io.InputStream;

public interface JsonProvider {

	String to(Object object);
	
	<T> T from(String json, Class<T> clazz);
	
	<T> T from(InputStream json, Class<T> clazz);
	
}
