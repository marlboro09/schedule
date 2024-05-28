package com.sparta.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI openAPI() {
		String jwt = "JWT";
		SecurityScheme securityScheme = new SecurityScheme()
			.name(jwt)
			.type(SecurityScheme.Type.HTTP)
			.scheme("bearer")
			.bearerFormat("JWT");

		SecurityRequirement securityRequirement = new SecurityRequirement().addList(jwt);

		return new OpenAPI()
			.components(new Components().addSecuritySchemes(jwt, securityScheme))
			.info(apiInfo())
			.addSecurityItem(securityRequirement);
	}

	private Info apiInfo() {
		return new Info()
			.title("API Test")
			.description("Let's practice Swagger UI")
			.version("1.0.0");
	}
}
