package com.vrcc.utils.cache.impl;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.lambdaworks.redis.RedisClient;
import com.lambdaworks.redis.RedisFuture;
import com.lambdaworks.redis.api.async.RedisAsyncCommands;
import com.lambdaworks.redis.codec.ByteArrayCodec;
import com.vrcc.utils.Serialization;
import com.vrcc.utils.cache.Cache;

@Singleton
public class Redis implements Cache {

	private final RedisAsyncCommands<byte[], byte[]> async;
	private final Serialization serialization;

	@Inject
	public Redis(RedisClient client, Serialization serialization) {
		this.async = client.connect(ByteArrayCodec.INSTANCE).async();
		this.serialization = serialization;
	}

	@Override
	public void put(String key, Serializable value, long seconds) {
		async.setex(key.getBytes(), seconds, serialization.serialize(value));
	}

	@Override
	@SuppressWarnings("unchecked")
	public Serializable get(String key) {
		final RedisFuture<byte[]> future = async.get(key.getBytes());
		try {
			return serialization.deserialize(future.get());
		} catch (Exception e) {
			return null;
		}
	}

}
