package com.vrcc.api.provider;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class DefaultExceptionMapper implements ExceptionMapper<Throwable> {

	@Override
	public Response toResponse(Throwable exception) {
		exception.printStackTrace();
		return Response.status(INTERNAL_SERVER_ERROR).entity(ExceptionResponse.from(exception)).type(APPLICATION_JSON)
				.build();
	}

}
