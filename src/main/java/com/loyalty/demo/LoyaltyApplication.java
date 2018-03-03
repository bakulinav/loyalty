package com.loyalty.demo;

import org.kie.api.runtime.KieContainer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class LoyaltyApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LoyaltyApplication.class, args);
	}

	KieContainer kc;

	public LoyaltyApplication(KieContainer kc) {
		this.kc = kc;
	}

	@Override
	public void run(String... strings) throws Exception {

	}
}
