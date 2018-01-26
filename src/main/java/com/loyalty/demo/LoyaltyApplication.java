package com.loyalty.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class LoyaltyApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoyaltyApplication.class, args);
	}
}
