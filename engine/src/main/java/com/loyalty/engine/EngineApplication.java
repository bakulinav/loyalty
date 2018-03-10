package com.loyalty.engine;

import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.command.Command;
import org.kie.api.command.KieCommands;
import org.kie.api.logger.KieLoggers;
import org.kie.api.runtime.ExecutionResults;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.StatelessKieSession;
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
public class EngineApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(EngineApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(EngineApplication.class, args);
	}

	private KieContainer kc;
	private KieLoggers kl;
    private KieServices ks;

	@Autowired
	public EngineApplication(KieContainer kc, KieLoggers kl, KieServices ks) {
		this.kc = kc;
		this.kl = kl;
        this.ks = ks;
	}

	@Override
	public void run(String... strings) throws Exception {
//		  statefulCase();
//        statelessCase();
//        statelessOverCommandCase();
        statelessBatchExecutorCase();
	}

	private void statefulCase() {
		log.info("Start Loyalty application");

		log.info("Getting KIE base");
		KieBase loyaltyKB = kc.getKieBase("LoyaltyKB");

		log.info("Getting KIE session");
		KieSession loyaltyKS = kc.newKieSession("LoyaltyKS"); // stateful session

		// GLOBALS
		List list= new ArrayList<Integer>();
		loyaltyKS.setGlobal("list", list);
		loyaltyKS.setGlobal("msg", "Hello");
		loyaltyKS.setGlobal("logger", LoggerFactory.getLogger("RulesProcessor"));

		// LISTENERS
//		loyaltyKS.addEventListener(new DebugAgendaEventListener());
//		loyaltyKS.addEventListener(new DebugRuleRuntimeEventListener());
//		loyaltyKS.addEventListener(new DebugProcessEventListener());

		// FACTS
		log.info("Add facts");
		ShoppingCart cart = new ShoppingCart("alex");
		loyaltyKS.insert(cart);
		log.info("Cart before processing. Discounted? " + cart.getDiscounted());

		// PROCESSING
		log.info("Run facts processing");
		loyaltyKS.fireAllRules();
		log.info("Cart after processing. Discounted? " + cart.getDiscounted());

		log.info("Clean up KIE session");
		loyaltyKS.dispose();
	}

	private void statelessCase() {
        log.info("Start Loyalty application");

        log.info("Getting KIE session");
        StatelessKieSession loyaltyKS = kc.newStatelessKieSession();// stateless session

        // GLOBALS
        List list= new ArrayList<Integer>();
        loyaltyKS.setGlobal("logger", LoggerFactory.getLogger("RulesProcessor"));

        // FACTS
        log.info("Add facts");
        ShoppingCart cart = new ShoppingCart("alex");
        log.info("Cart before processing. Discounted? " + cart.getDiscounted());

        // PROCESSING
        log.info("Run facts processing");
        loyaltyKS.execute(cart);
        log.info("Cart after processing. Discounted? " + cart.getDiscounted());

        log.info("Clean up KIE session");
    }

    // works like "onlyMatch"
    private void statelessOverCommandCase() {
        log.info("Start Loyalty application");

        log.info("Getting KIE session");
        StatelessKieSession loyaltyKS = kc.newStatelessKieSession();// stateless session

        // GLOBALS
        List list= new ArrayList<Integer>();
        loyaltyKS.setGlobal("logger", LoggerFactory.getLogger("RulesProcessor"));

        // FACTS
        log.info("Add facts");
        ShoppingCart cart = new ShoppingCart("bob");
        CartItem item = new CartItem("iphone");
        cart.setShoppingItems(Arrays.asList(item));

        log.info("Cart before processing. Discounted? " + cart.getDiscounted());

        // COMMAND
        Command command = ks.getCommands().newInsertElements(Arrays.asList(cart, item));

        // PROCESSING
        log.info("Run facts processing");
        loyaltyKS.execute(command);
        log.info("Cart after processing. Discounted? " + cart.getDiscounted());

        log.info("Clean up KIE session");
    }

    // works like "ifAny"
    private void statelessBatchExecutorCase() {
        log.info("Start Loyalty application");

        log.info("Getting KIE session");
        StatelessKieSession loyaltyKS = kc.newStatelessKieSession();// stateless session

        // GLOBALS
        List list= new ArrayList<Integer>();
        loyaltyKS.setGlobal("logger", LoggerFactory.getLogger("RulesProcessor"));

        // FACTS
        log.info("Add facts");
        ShoppingCart cartOne = new ShoppingCart("Bob");
        CartItem itemOne = new CartItem("iphone");
        cartOne.setShoppingItems(Arrays.asList(itemOne));

        ShoppingCart cartTwo = new ShoppingCart("Mike");
        CartItem itemTwo = new CartItem("samsung");
        cartTwo.setShoppingItems(Arrays.asList(itemTwo));

        log.info("Cart from " + cartOne.customerId + " before processing. Discounted? " + cartOne.getDiscounted());
        log.info("Cart from " + cartTwo.customerId + " before processing. Discounted? " + cartTwo.getDiscounted());

        // BATCH
        KieCommands kieCommands = ks.getCommands();
        ArrayList<Command> cmds = new ArrayList<>();
        cmds.add(kieCommands.newInsertElements(Arrays.asList(cartOne, cartOne.getShoppingItems().get(0)), "BobCart", true, null));
        cmds.add(kieCommands.newInsertElements(Arrays.asList(cartTwo, cartTwo.getShoppingItems().get(0)), "MikeCart", true, null));

        // PROCESSING
        log.info("Run commands processing");
        ExecutionResults result = loyaltyKS.execute(kieCommands.newBatchExecution(cmds));
        log.info("Cart from " + cartOne.customerId + " after processing. Discounted? " + cartOne.getDiscounted());
        log.info("Cart from " + cartTwo.customerId + " after processing. Discounted? " + cartTwo.getDiscounted());

        log.info("Clean up KIE session");
    }
}
