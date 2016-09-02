package com.vrcc.api.filter;

import java.util.UUID;

public class Tickets {

	private final ThreadLocal<String> tickets = new ThreadLocal<>();

	public String get() {
		if (tickets.get() == null) {
			tickets.set(UUID.randomUUID().toString());
		}
		return tickets.get();
	}

	public void clean() {
		tickets.remove();
	}

}
