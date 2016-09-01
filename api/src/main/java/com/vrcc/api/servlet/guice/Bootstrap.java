package com.vrcc.api.servlet.guice;

import static com.google.inject.Guice.createInjector;

import javax.servlet.annotation.WebListener;

import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

@WebListener
public class Bootstrap extends GuiceServletContextListener {

	@Override
	protected Injector getInjector() {
		return createInjector(new ApiModule(), new InfraModule(), new CoreModule());
	}

}
