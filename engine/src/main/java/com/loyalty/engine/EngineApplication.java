package com.loyalty.engine;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class EngineApplication {

	private static final Logger log = LoggerFactory.getLogger(EngineApplication.class);

	public static void main(String[] args) {
        log.info("Run EngineApplication");
		SpringApplication.run(EngineApplication.class, args);
	}
}
