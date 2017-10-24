package edu.ldcollege;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = "edu.ldcollege.**")
@MapperScan("edu.ldcollege.mapping")
public class App_edu extends SpringBootServletInitializer{
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(App_edu.class);
	}
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(App_edu.class);
	}
}
