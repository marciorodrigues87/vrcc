package com.vrcc.api.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

public interface ResponseWriter {

	void write(HttpServletResponse response, Object object) throws IOException;

}