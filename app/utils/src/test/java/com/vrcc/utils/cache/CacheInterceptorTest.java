package com.vrcc.utils.cache;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.Serializable;
import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInvocation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CacheInterceptorTest {

	private static final String KEY = "key";

	private static final long TTL = 10;

	@Mock
	private CacheKeyGenerator keyGenerator;

	@Mock
	private Cache cache;

	@InjectMocks
	private CacheInterceptor interceptor;

	@Mock
	private MethodInvocation invocation;

	@Test
	public void shouldUseCachedValueIfExists() throws Throwable {
		final Object value = annotated();
		when(keyGenerator.keyFor(any(Method.class), any(Object[].class))).thenReturn(KEY);
		when(cache.get(KEY)).thenReturn(value);
		final Object returnValue = interceptor.invoke(invocation);
		assertEquals(value, returnValue);
	}

	@Test
	public void shouldProcceedInvocationIfValueIsNotCached() throws Throwable {
		final Serializable value = annotated();
		when(keyGenerator.keyFor(any(Method.class), any(Object[].class))).thenReturn(KEY);
		when(cache.get(KEY)).thenReturn(null);
		when(invocation.proceed()).thenReturn(value);
		when(invocation.getMethod()).thenReturn(this.getClass().getMethod("annotated"));
		final Object returnValue = interceptor.invoke(invocation);
		assertEquals(value, returnValue);
		verify(cache).put(KEY, value, TTL);
	}

	@Test
	public void shouldNotCacheNullValue() throws Throwable {
		final Serializable value = null;
		when(keyGenerator.keyFor(any(Method.class), any(Object[].class))).thenReturn(KEY);
		when(cache.get(KEY)).thenReturn(null);
		when(invocation.proceed()).thenReturn(value);
		when(invocation.getMethod()).thenReturn(this.getClass().getMethod("annotated"));
		final Object returnValue = interceptor.invoke(invocation);
		assertNull(returnValue);
		verify(cache, never()).put(KEY, value, TTL);
	}

	@Test(expected = RuntimeException.class)
	public void shouldNotAcceptNonSerializableValue() throws Throwable {
		when(keyGenerator.keyFor(any(Method.class), any(Object[].class))).thenReturn(KEY);
		when(cache.get(KEY)).thenReturn(null);
		when(invocation.proceed()).thenReturn(new Object());
		interceptor.invoke(invocation);
	}

	@Cached(ttl = TTL)
	public Serializable annotated() {
		return new Serializable() {
			private static final long serialVersionUID = 1L;
		};
	}

}
