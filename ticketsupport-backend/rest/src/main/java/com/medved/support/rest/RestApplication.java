package com.medved.support.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.medved.support")
@EnableJpaRepositories(basePackages={"com.medved.support.repository.interfaces"})
public class RestApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApplication.class, args);
				
	}

}
