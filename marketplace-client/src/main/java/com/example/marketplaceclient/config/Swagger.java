package com.example.marketplaceclient.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Swagger {
    @Bean
    public OpenAPI customOpenApi() {

        return new OpenAPI()
                .info(new Info()
                        .title("Houlala Marketplace Client api")
                        .version("1.0.0")
                        .description("Le Client du marketplace")
                        .license(new License().name("MIT"))
                );
    }

}
