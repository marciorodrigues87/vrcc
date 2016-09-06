package com.vrcc.utils.cache.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.Serializable;
import java.util.concurrent.ExecutionException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.RedisFuture;
import com.lambdaworks.redis.api.StatefulRedisConnection;
import com.lambdaworks.redis.api.async.RedisAsyncCommands;
import com.lambdaworks.redis.codec.ByteArrayCodec;
import com.vrcc.utils.serialization.SerializationProvider;

@RunWith(MockitoJUnitRunner.class)
public class RedisTest {

	private static final String KEY = "key";
	private static final String VALUE = "value";
	private static final long TTL = 100L;

	@Mock
	private SerializationProvider serialization;

	@Mock
	private RedisClient client;

	private Redis redis;

	@Mock
	private StatefulRedisConnection<byte[], byte[]> connection;

	@Mock
	private RedisAsyncCommands<byte[], byte[]> commands;

	@Mock
	private RedisFuture<byte[]> future;

	@Before
	public void before() {
		when(client.connect(ByteArrayCodec.INSTANCE)).thenReturn(connection);
		when(connection.async()).thenReturn(commands);
		redis = new Redis(client, serialization);
	}

	@Test
	public void shouldPutValue() {
		when(serialization.serialize(VALUE)).thenReturn(VALUE.getBytes());
		redis.put(KEY, VALUE, TTL);
		verify(commands).setex(KEY.getBytes(), TTL, VALUE.getBytes());
	}

	@Test
	public void putShouldNotPropagateExcaptions() {
		when(serialization.serialize(VALUE)).thenThrow(new RuntimeException("mocked"));
		redis.put(KEY, VALUE, TTL);
	}

	@Test
	public void shouldGetValue() throws InterruptedException, ExecutionException {
		when(commands.get(KEY.getBytes())).thenReturn(future);
		when(future.get()).thenReturn(VALUE.getBytes());
		when(serialization.deserialize(VALUE.getBytes())).thenReturn(VALUE);
		final Serializable serializable = redis.get(KEY);
		assertEquals(VALUE, serializable);
	}

	@Test
	public void getShouldNotPropagateExceptions() throws InterruptedException, ExecutionException {
		when(commands.get(KEY.getBytes())).thenReturn(future);
		when(future.get()).thenThrow(new RuntimeException("mocked"));
		final Serializable serializable = redis.get(KEY);
		assertNull(serializable);
	}

}
