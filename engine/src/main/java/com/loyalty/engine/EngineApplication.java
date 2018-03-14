package com.loyalty.engine;

import com.loyalty.model.EngineMode;
import com.loyalty.model.ShoppingCart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
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
        // client 1
        ShoppingCart cartClient1 = new ShoppingCart("alex");
        log.info("Cart before: Discounted="+ cartClient1.getDiscounted());

        this.processor.process(EngineMode.STATELESS, cartClient1, "clientID1");

        log.info("Cart after: Discounted="+ cartClient1.getDiscounted());

        // client 2
        ShoppingCart cartClient2 = new ShoppingCart("mike");
        log.info("Cart before: Discounted="+ cartClient2.getDiscounted());

        this.processor.process(EngineMode.STATELESS, cartClient2, "clientID2");

        log.info("Cart after: Discounted="+ cartClient2.getDiscounted());
    }
}
