package com.tarasvakaryuk.aett.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
          .groupName("All")
          .apiInfo(new ApiInfoBuilder().title("Aett API")
                                       .description("Call Rest controllers")
                                       .version("1.0").build())
          .select()
          .apis(RequestHandlerSelectors.basePackage("com.tarasvakaryuk.aett.controllers"))
          .build();
    }

}