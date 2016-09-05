package com.vrcc.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Serialization {

	public byte[] serialize(Serializable o) {
		if (o == null) {
			throw new IllegalArgumentException();
		}
		try (ByteArrayOutputStream bos = new ByteArrayOutputStream(); //
				ObjectOutputStream os = new ObjectOutputStream(bos)) {
			os.writeObject(o);
			return bos.toByteArray();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public <T> T deserialize(byte[] in) {
		if (in == null) {
			throw new IllegalArgumentException();
		}
		try (ByteArrayInputStream bis = new ByteArrayInputStream(in); //
				ObjectInputStream is = new ObjectInputStream(bis)) {
			return (T) is.readObject();
		} catch (IOException | ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
}
