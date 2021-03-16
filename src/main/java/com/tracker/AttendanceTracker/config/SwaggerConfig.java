package com.tracker.AttendanceTracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Configuration
	public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	    @Override
	    public void configure(HttpSecurity http) throws Exception {

	      http.authorizeRequests().antMatchers("/xxx/**").permitAll();          
	      
	      http.csrf().disable();

	    }

	}
	 @Bean
	    public Docket productApi() {
	        return new Docket(DocumentationType.SWAGGER_2)
	                .select().apis(RequestHandlerSelectors.basePackage("com.xxx"))
	                .paths(PathSelectors.regex("/xxx/.*"))
	                .build();
	    }

}
