package com.example.authbivservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.retry.annotation.EnableRetry;

@SpringBootApplication
@ConfigurationPropertiesScan("com.example.authbivservice.config")
@EnableJpaAuditing
@EnableRetry
public class AuthBivServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthBivServiceApplication.class, args);
	}

}
