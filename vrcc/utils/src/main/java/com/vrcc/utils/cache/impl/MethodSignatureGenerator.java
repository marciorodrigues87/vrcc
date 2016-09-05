package com.vrcc.utils.cache.impl;

import java.lang.reflect.Method;

import javax.inject.Singleton;

import com.vrcc.utils.cache.CacheKeyGenerator;

@Singleton
public class MethodSignatureGenerator implements CacheKeyGenerator {

	@Override
	public String keyFor(Method method, Object[] args) {
		final StringBuilder sb = new StringBuilder();
		appendClassAndMethod(method, sb);
		appendParameterTypes(method, sb);
		appendArgs(args, sb);
		final String key = sb.toString();
		return key;
	}

	private static void appendClassAndMethod(Method method, final StringBuilder sb) {
		sb.append(method.getDeclaringClass().getSimpleName()).append("/").append(method.getName()).append("/")
				.append(method.getReturnType().getSimpleName()).append("/");
	}

	private static void appendArgs(Object[] args, final StringBuilder sb) {
		if (args != null && args.length > 0) {
			for (Object object : args) {
				sb.append(object).append("_");
			}
		}
		sb.deleteCharAt(sb.length() - 1);
	}

	private static void appendParameterTypes(Method method, final StringBuilder sb) {
		final Class<?>[] parameterTypes = method.getParameterTypes();
		if (parameterTypes != null && parameterTypes.length > 0) {
			for (Class<?> parameterType : parameterTypes) {
				sb.append(parameterType.getSimpleName()).append("/");
			}
		}
	}

}
