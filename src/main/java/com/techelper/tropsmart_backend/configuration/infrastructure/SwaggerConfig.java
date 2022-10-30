package com.techelper.tropsmart_backend.configuration.infrastructure;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig
{

    @Bean(name= "bloggingOpenApi")
    public OpenAPI tsOpenApi(){
        return new OpenAPI()
                .components(new Components())
                .info(new Info()
                        .title("Tropsmart API").description("Tropsmart API for dummies"));
    }
}
