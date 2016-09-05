package com.vrcc.utils.cache;

import java.io.Serializable;

public interface Cache {

	void put(String key, Serializable value, long seconds);

	<T extends Serializable> T get(String key);

}
