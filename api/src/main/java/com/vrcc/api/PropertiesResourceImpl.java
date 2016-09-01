package com.vrcc.api;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.Valid;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.vrcc.core.facade.PropertyFacade;
import com.vrcc.domain.Properties;
import com.vrcc.domain.Property;
import com.vrcc.domain.PropertyFilter;

@Singleton
@Resource
@Path("/properties")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public class PropertiesResourceImpl {

	private final PropertyFacade facade;

	@Inject
	public PropertiesResourceImpl(PropertyFacade facade) {
		this.facade = facade;
	}

	@POST
	public Property add(@Valid Property request) {
		return facade.add(request);
	}

	@GET
	@Path("/{id}")
	public Property get(@PathParam("id") long id) {
		return facade.get(id);
	}

	@GET
	public Properties find(@BeanParam @Valid PropertyFilter filter) {
		return facade.find(filter);
	}

}
