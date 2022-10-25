package veterinaria.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

/**
 * Configuration Swagger for the API REST
 *
 * HTML: http://127.0.0.1:8090//veterinary/v1/swagger-ui/
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiDetails())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiDetails() {
        return new ApiInfo(
                "Springboot API REST",
                "Library API Rest Docs",
                "1.0",
                "http://www.springboot.org/docs/",
                new Contact("ed", "http://www.springboot.org", "ed@google.com"),
                "MIT",
                "http://www.google.com",
                Collections.emptyList());
    }
}
