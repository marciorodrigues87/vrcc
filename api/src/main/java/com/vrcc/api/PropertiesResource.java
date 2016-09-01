package com.vrcc.api;

import static com.vrcc.utils.Regexes.NUMERIC;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import java.util.Collection;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.vrcc.api.converter.PropertiesResponseConverter;
import com.vrcc.api.converter.PropertyFilterParamConverter;
import com.vrcc.api.converter.PropertyRequestConverter;
import com.vrcc.api.converter.PropertyResponseConverter;
import com.vrcc.api.domain.PropertiesResponse;
import com.vrcc.api.domain.PropertyFilterParam;
import com.vrcc.api.domain.PropertyRequest;
import com.vrcc.api.domain.PropertyResponse;
import com.vrcc.core.facade.PropertyFacade;
import com.vrcc.domain.Property;
import com.vrcc.domain.PropertyFilter;

@Singleton
@Resource
@Path("/properties")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public class PropertiesResource {

	private final PropertyFacade facade;
	private final PropertiesResponseConverter propertiesResponseConverter;
	private final PropertyResponseConverter propertyResponseConverter;
	private final PropertyFilterParamConverter propertyFilterParamConverter;
	private final PropertyRequestConverter propertyRequestConverter;

	@Inject
	public PropertiesResource(PropertyFacade facade, PropertiesResponseConverter propertiesResponse,
			PropertyResponseConverter propertyResponseConverter, PropertyRequestConverter propertyRequestConverter,
			PropertyFilterParamConverter propertyFilterParamConverter) {
		this.facade = facade;
		this.propertiesResponseConverter = propertiesResponse;
		this.propertyResponseConverter = propertyResponseConverter;
		this.propertyRequestConverter = propertyRequestConverter;
		this.propertyFilterParamConverter = propertyFilterParamConverter;
	}

	@POST
	public PropertyResponse add(@Valid PropertyRequest request) {
		final Property property = propertyRequestConverter.convert(request);
		final Property addedProperty = facade.add(property);
		return propertyResponseConverter.convert(addedProperty);
	}

	@GET
	@Path("/{id}")
	public PropertyResponse get(@Pattern(regexp = NUMERIC) @PathParam("id") String paramId) {
		final long id = propertyResponseConverter.convert(paramId);
		final Property property = facade.get(id);
		return propertyResponseConverter.convert(property);
	}

	@GET
	public PropertiesResponse find(@BeanParam @Valid PropertyFilterParam filterParam) {
		final PropertyFilter filter = propertyFilterParamConverter.convert(filterParam);
		final Collection<Property> properties = facade.find(filter);
		return propertiesResponseConverter.convert(properties);
	}

}
