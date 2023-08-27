package com.playground.core.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Value("${playground.title}")
    private String title;

    @Value("${playground.version}")
    private String version;

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(info());
    }

    private Info info() {
        License license = new License();
        license.setUrl("https://github.com/dongkyunkimdev/spring-playground");
        license.setName("Github");

        return new Info()
                .title(this.title)
                .version(this.version)
                .license(license);
    }

}
