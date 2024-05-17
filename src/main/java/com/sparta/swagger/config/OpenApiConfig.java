package com.sparta.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {
	/**
	 * OpenAPI bean 구성
	 * @return
	 */
	@Bean
	public OpenAPI openApi() {
		Info info = new Info()
			    .title("Swagger API")
			    .version("1.0.0")
				.description("Swagger Test");
        return new OpenAPI()
			.components(new Components())
			.info(info);
    }
}
