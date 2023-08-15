package com.example.demo;

import org.apache.poi.sl.usermodel.ObjectMetaData;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class ServeurTpmApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ServeurTpmApplication.class, args);
	}


	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(ServeurTpmApplication.class);
	}

	/*@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(ServeurTpmApplication.class);
	}*/

}
