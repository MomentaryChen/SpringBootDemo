package com.momentary.demo.config;

import java.lang.annotation.Annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class SwaggerConfig {
	
	@Bean
	public OpenAPI openAPI() {
		Info info = new Info()
				.title("Momentary Spring Boot Demo")
				.version("Version 1.0.0.")
				.description("此文件顯示所有的Demo API");
		 
		String securitySchemeNameString = "JWT AUthentication";
		SecurityRequirement securityRequirement=
				new SecurityRequirement().addList(securitySchemeNameString);
		
		Components components = new Components()
				.addSecuritySchemes(securitySchemeNameString, 
						new SecurityScheme()
						.name(securitySchemeNameString)
						.type(SecurityScheme.Type.HTTP)
						.scheme("Bearer")
						.bearerFormat("JWT"));
				
		
		return new OpenAPI()
				.info(info)
				.addSecurityItem(securityRequirement)
				.components(components);	
	}
}
