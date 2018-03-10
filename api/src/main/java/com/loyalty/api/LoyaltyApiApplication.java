package com.loyalty.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class LoyaltyApiApplication {

	private static final Logger log = LoggerFactory.getLogger(LoyaltyApiApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(LoyaltyApiApplication.class, args);
	}

}
