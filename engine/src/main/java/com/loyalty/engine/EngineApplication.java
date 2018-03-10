package com.loyalty.engine;

import com.loyalty.engine.model.ShoppingCart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableWebMvc
public class EngineApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(EngineApplication.class);

	public static void main(String[] args) {
        log.info("Run EngineApplication");
		SpringApplication.run(EngineApplication.class, args);
	}

    public RulesProcessor processor;

    public EngineApplication(RulesProcessor processor) {
        this.processor = processor;
    }

    @Override
    public void run(String... strings) throws Exception {

        ShoppingCart cart = new ShoppingCart("alex");
        log.info("Cart before: Discounted="+ cart.getDiscounted());

        this.processor.process(EngineMode.STATELESS, cart, "clientId");

        log.info("Cart after: Discounted="+ cart.getDiscounted());
    }
}
