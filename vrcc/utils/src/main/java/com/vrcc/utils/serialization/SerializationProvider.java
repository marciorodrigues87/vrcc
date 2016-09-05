package com.vrcc.utils.serialization;

import java.io.Serializable;

public interface SerializationProvider {

	byte[] serialize(Serializable o);

	<T> T deserialize(byte[] in);

}