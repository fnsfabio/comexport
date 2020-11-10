package com.br.fns.comexport.doc;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket postApi(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("faq-api")
                .apiInfo(apiInfo()).select().paths(postPaths()).build();
    }

    private Predicate<String> postPaths() {
        return or(regex("/user.*"),
                  regex("/role.*"));
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("Comexport", "http://www.comexport.net.br", "fnsfabio@hotmail.com");
        return new ApiInfoBuilder().title("Comexport Faq API")
                .description("Teste Comexport")
                .termsOfServiceUrl("http://www.comexport.net.br/")
                .contact(contact).license("GitHub Licenses API")
                .licenseUrl("https://www.programmableweb.com/api/github-licenses").version("0.0.1").build();
    }
}
