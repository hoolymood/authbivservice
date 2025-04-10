package com.example.authbivservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("com.example.authbivservice.config")
public class AuthBivServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthBivServiceApplication.class, args);
	}

}
