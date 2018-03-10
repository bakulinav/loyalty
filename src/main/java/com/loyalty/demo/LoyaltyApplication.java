package com.loyalty.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
//@EnableWebMvc
public class LoyaltyApplication {

	private static final Logger log = LoggerFactory.getLogger(LoyaltyApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(LoyaltyApplication.class, args);
	}

}
