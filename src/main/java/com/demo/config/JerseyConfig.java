package com.demo.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.demo.controller.AccountRestService;

@Component
@ApplicationPath("/v1")
public class JerseyConfig extends ResourceConfig {
	public JerseyConfig() {
		registerEndpoints();
	}

	private void registerEndpoints() {
		register(JacksonFeature.class);
		register(AccountRestService.class);
		packages("com.demo", "com.demo.controller", "com.demo.dto");
	}
}