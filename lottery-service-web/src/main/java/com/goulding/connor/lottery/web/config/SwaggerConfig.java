package com.goulding.connor.lottery.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Connor Goulding
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.goulding.connor.lottery"))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "Lottery Service REST API",
                "Minimalistic Lottery Service for some fun.",
                "1.0",
                "Terms of service",
                new Contact("Connor Goulding", "www.goulding.com", "connor@goulding.com"),
                "Apache License",
                "http://www.apache.org/licenses/LICENSE-2.0");
        return apiInfo;
    }
}
