package com.stockmarketapp.stocksmanagementservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguration {

	@Bean
	public Docket mySwaggerConfiguration() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.ant("/**"))
				.apis(RequestHandlerSelectors.basePackage("com.stockmarketapp"))
				.build()
				.apiInfo(apiInfo())
				;
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Stock Management Services")
				.description("Service the users can manage the company and its respective stocks.")
				.version("5.4")
				.contact(new Contact("Shyam Sundar M", "http://example.com", "shaamsundar16@gmail.com"))
				.build()
				;
	}
}
