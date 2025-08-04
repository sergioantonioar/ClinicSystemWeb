package org.example.clinic_system.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Clinic System API")
                        .version("1.0")
                        .description("API para la gestión del sistema clínico"))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", // debe coincidir con SwaggerConfig
                                new SecurityScheme()
                                        .name("bearerAuth") // <- este es el cambio
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                        )
                )
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth")); // debe coincidir también
    }
}
