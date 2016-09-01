package com.vrcc.api.servlet.impl;

import static javax.servlet.http.HttpServletResponse.*;

import java.io.IOException;
import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import com.vrcc.api.servlet.ResponseWriter;
import com.vrcc.utils.json.JsonProvider;

public class SimpleResponseWriter implements ResponseWriter {

	private final JsonProvider json;

	@Inject
	public SimpleResponseWriter(JsonProvider json) {
		this.json = json;
	}

	public void write(HttpServletResponse response, Object object)
			throws IOException {
		try (final PrintWriter writer = response.getWriter()) {
			response.setContentType("application/json");
			response.setStatus(object != null ? SC_OK : SC_NOT_FOUND);
			writer.write(json.to(object));
			writer.flush();
		}
	}

}
