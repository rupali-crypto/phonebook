//package phonebook.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import io.swagger.v3.oas.annotations.OpenAPIDefinition;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//
//
//@OpenAPIDefinition
//public class SwaggerConfig {
//	@Bean
//	public Docket swaggerApi() {
//		
//		return new Docket(DocumentationType.SWAGGER_2)
//				.select()
//				.apis(RequestHandlerSelectors.basePackage("phonebook.controllers"))
//				.paths(PathSelectors.ant("/api/*"))
//				.build();
//	}
//
//}
