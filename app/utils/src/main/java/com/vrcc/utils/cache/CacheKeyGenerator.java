package com.vrcc.utils.cache;

import java.lang.reflect.Method;

public interface CacheKeyGenerator {

	String keyFor(Method method, Object[] args);

	String keyPattern(Method method);

}