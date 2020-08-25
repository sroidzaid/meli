package examen.meli.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket meliApi() {
        final String swaggerToken = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJuYmYiOjE1OTUwOTg0NDYsInJvbGVzIjpbIlJPTEVfQURNSU4iXSwiaXNzIjoiZnlvcC10b2tlbiIsImlhdCI6MTU5NTA5ODQ0NiwidXNlciI6ImxlYW4ifQ.j117kWjFl_Bw0OBVUEKSQq8UKzoCrtif5YSX0hWv_Nk";
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("examen.meli.api"))
                //.paths(regex(".*"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metaInfo());


    }

    private ApiInfo metaInfo() {

        ApiInfo apiInfo = new ApiInfo(
                "Api exámen Meli",
                "Servicio de información de accesos IP",
                "1.0",
                "Term of service",
                "sebastian.roidzaid@gmail.com",
                "Apache License Version 1.0",
                "https://www.apache.org/license.html"
        );
        return apiInfo;
    }

    Parameter authHeader = new ParameterBuilder()
            .parameterType("header")
            .name("Authorization")
            .modelRef(new ModelRef("string"))
            .build();
}
