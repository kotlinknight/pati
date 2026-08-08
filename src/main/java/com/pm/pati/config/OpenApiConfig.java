package com.pm.pati.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info =
                @Info(
                        title = "Order Management API",
                        version = "1.0",
                        description = "Phase 1 of Order Management CRUD system",
                        contact = @Contact(name = "pati Developer")))
public class OpenApiConfig {}
