package consulta.cidade.brasil.swagger;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {

  @Bean
  public Docket apiDoc() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("consulta.cidade.brasil"))
        .paths(regex("/api/v1.*"))
        .build()
        .apiInfo(metaData());

  }

  private ApiInfo metaData() {
    return new ApiInfoBuilder()
        .title("Consulta de Cidades do Brasil")
        .description("API para consultas da distância entre cidades do Brasil")
        .version("1.0.0")
        .contact(new Contact("Marcelo dos Santos de Oliveira",
                "https://www.linkedin.com/in/marcelofullstack/",
                "msbobsk8@gmail.com"))
        .license("Apache License Version 2.0")
        .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
        .build();
  }
  
  @Override
  protected void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("swagger-ui.html")
        .addResourceLocations("classpath:/META-INF/resources/");

    registry.addResourceHandler("/webjars/**")
        .addResourceLocations("classpath:/META-INF/resources/webjars/");
  }
  
  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
      argumentResolvers.add( new PageableHandlerMethodArgumentResolver());
  }
  
}
