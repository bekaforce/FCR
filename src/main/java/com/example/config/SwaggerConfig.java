package com.example.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI FcrApi() {
        return new OpenAPI().info(new Info()
                .title("Fcr Api")
                .version("1.0.0")
                .contact(new Contact()
                        .email("BOZhanakeev@beeline.kg")
                        .url("http://localhost:8080/")
                        .name("Zhanakeev Bektursun")));
    }
}
