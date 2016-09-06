package com.vrcc.utils.cache;

import java.lang.reflect.Method;

import javax.inject.Inject;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class ExpiringCacheInterceptor implements MethodInterceptor {

	@Inject
	private CacheKeyGenerator keyGenerator;

	@Inject
	private Cache cache;

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		for (Method method : invocation.getMethod().getDeclaringClass().getDeclaredMethods()) {
			if (invocation.getMethod().getAnnotation(Expires.class).methodName().equals(method.getName())) {
				cache.remove(keyGenerator.keyPattern(method));
			}
		}
		return invocation.proceed();
	}

}
