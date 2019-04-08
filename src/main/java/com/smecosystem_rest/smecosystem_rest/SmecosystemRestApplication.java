package com.smecosystem_rest.smecosystem_rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SmecosystemRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmecosystemRestApplication.class, args);
	}

}
