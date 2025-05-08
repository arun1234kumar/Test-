package com.trimble.carstarter.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;

import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "IESCMS", version = "1.0", description = "Oasys Excise Department Application", contact = @Contact(name = "")), security = {
		@SecurityRequirement(name = "bearerToken") })
@SecuritySchemes({
		@SecurityScheme(name = "x-authorization", type = SecuritySchemeType.HTTP,  in=SecuritySchemeIn.HEADER, scheme = "bearer", bearerFormat = "JWT") })
public class SwaggerConfig {

}
	