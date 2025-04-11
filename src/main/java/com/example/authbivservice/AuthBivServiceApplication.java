package com.example.authbivservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@ConfigurationPropertiesScan("com.example.authbivservice.config")
@EnableJpaAuditing
public class AuthBivServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthBivServiceApplication.class, args);
	}

}
