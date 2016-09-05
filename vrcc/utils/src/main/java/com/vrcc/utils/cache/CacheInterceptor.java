package com.vrcc.utils.cache;

import static java.lang.String.format;

import java.io.Serializable;

import javax.inject.Inject;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class CacheInterceptor implements MethodInterceptor {

	@Inject
	private CacheKeyGenerator keyGenerator;

	@Inject
	private Cache cache;

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		final String key = keyGenerator.keyFor(invocation.getMethod(), invocation.getArguments());
		final Serializable cached = cache.get(key);
		if (cached != null) {
			return cached;
		}
		final Object object = invocation.proceed();
		if (!(object instanceof Serializable)) {
			throw new RuntimeException(format("return class %s is not Serializable", object.getClass()));
		}
		final Serializable serializable = (Serializable) object;
		if (serializable != null) {
			cache.put(key, serializable, invocation.getMethod().getAnnotation(Cached.class).ttl());
		}
		return serializable;
	}

}
