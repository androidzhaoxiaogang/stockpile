package com.capitalone.nsb.marketing.stockpile.config;

import com.fasterxml.classmate.TypeResolver;
import com.google.common.base.Predicates;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.schema.ModelReference;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.*;

import static com.google.common.collect.Lists.*;
import static com.google.common.collect.Maps.*;

/**
 * Created by Selwyn Lehmann on 2/12/16.
 *
 * For all configuration options look at the reference and api documentation:
 * - http://springfox.github.io/springfox/docs/current/
 * - http://springfox.github.io/springfox/javadoc/current/
 *
 * To see the swagger json: GET http://localhost:8080/v2/api-docs
 * To see the swagger ui: GET http://localhost:8080/swagger-ui.html
 */
@Configuration
@EnableSwagger2
@Import({BeanValidatorPluginsConfiguration.class})
@ComponentScan({"com.capitalone.nsb.marketing.stockpile"})
public class SwaggerConfig {

    @Autowired
    private TypeResolver typeResolver;

    @Bean
    public Docket apiDocumentation() {

        return new Docket(DocumentationType.SWAGGER_2)

                // Select the controllers we want to expose
                .select()
                // Generate docs for all the controllers
                .apis(RequestHandlerSelectors.basePackage("com.capitalone.nsb.marketing.stockpile"))
                //exclude the error controller
                .paths(Predicates.not(PathSelectors.regex("/error")))
                .build()

                .useDefaultResponseMessages(false)

                //http://racksburg.com/choosing-an-http-status-code/
                .globalResponseMessage(RequestMethod.GET, sharedResponseMessages())
                .globalResponseMessage(RequestMethod.POST, sharedResponseMessages())
                .globalResponseMessage(RequestMethod.PUT, sharedResponseMessages())
                .globalResponseMessage(RequestMethod.DELETE, sharedResponseMessages())

                // Substitute UUID and DateTime with String when rendering a model's properties' types
                .directModelSubstitute(UUID.class, String.class)
                .directModelSubstitute(DateTime.class, String.class)
                .directModelSubstitute(Locale.class, String.class)
                //.directModelSubstitute(Channel.class, Enum.class)

               // .additionalModels(typeResolver.resolve(Response.class))

                //When we need to, we can expose information regarding the usage of an API Key, Basic Auth, or OAuth
                //.securitySchemes(securitySchemes())

                // And specify which endpoints are protected by security
                //.securityContexts(securityContext())

                .globalOperationParameters(
                        newArrayList(new ParameterBuilder()
                                .name(SecurityConfig.CREDS)
                                .description("Secured credentials we expect from our client.")
                                .modelRef(new ModelRef("string"))
                                .parameterType("header")
                                .required(true)
                                .build()))

                // Specify API information
                .apiInfo(apiInfo());
    }

    private ArrayList<ResponseMessage> sharedResponseMessages() {

        HashMap<String, ModelReference> headerMap = newHashMap();
        headerMap.put("xx-error-code", new ModelRef("Document error code, e.g. 1001-invalidAddress"));
        headerMap.put("xx-error-desc", new ModelRef("Message intended to help user self-service."));

        return newArrayList(
            new ResponseMessageBuilder()
                    .code(429)
                    .message("Too Many Requests. Retry the request after a delay")
                    //.headers(headerMap)
                    .build(),
            new ResponseMessageBuilder()
                    .code(503)
                    .message("Service Unavailable.")
                    //.responseModel(new ModelRef(NAPIResponse.class.getSimpleName()))
                    //.headers(headerMap)
                    .build());
    }

    @Bean
    UiConfiguration uiConfig() {
        return new UiConfiguration(""); // validationUrl

                // Future configurations:
                //"none",       // docExpansion          => none | list
                //"alpha",      // apiSorter             => alpha
                //"schema",     // defaultModelRendering => schema
                //false,        // enableJsonEditor      => true | false
                //true);        // showRequestHeaders    => true | false
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Capital One",//Title
                "We build APIs",//Description
                "v1",//Version
                "",//Terms of service
                new Contact("Paul Giles","","paul.giles@capitalone.com"),//Contact(name, url, email)
                "",//License
                "" //License Url
        );
    }
}
