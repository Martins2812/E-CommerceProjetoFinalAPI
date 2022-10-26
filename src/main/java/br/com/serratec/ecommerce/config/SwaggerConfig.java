package br.com.serratec.ecommerce.config;

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
public class SwaggerConfig {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("br.com.serratec.ecommerce"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfoBuilder().title("API de Teste")
				.description("Essa é uma API desenvolvida pelo grupo 7</br>"
						+ "</br>PROJETO FINAL API RESTful - RESIDÊNCIA SERRATEC 2022"
						+ "<p><strong> Lucas Martins, Pedro Cunha, Francisco Torres, Paulo Mayorm, Lucas Marques </strong></p>")
				.license("Apache License Version 2.0")
				.licenseUrl("https://www.apache.org/license/LICENSE-2.0")
				.version("1.0.0")
				.contact(new Contact("Repositório API GITHUB", "https://github.com/Martins2812/E-CommerceProjetoFinalAPI", "grupo7api@gmail.com"))
				.build();
		return apiInfo;
	}
}
