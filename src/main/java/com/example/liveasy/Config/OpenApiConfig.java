package com.example.liveasy.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Load & Booking Management API")
                        .version("1.0")
                        .description("API for managing loads and bookings in a logistics system")
                        .contact(new Contact()
                                .name("Liveasy")
                                .email("careers@liveasy.net.in")));
    }
}