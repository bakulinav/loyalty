package com.loyalty.demo;

import org.drools.core.event.DebugProcessEventListener;
import org.kie.api.KieBase;
import org.kie.api.event.rule.DebugAgendaEventListener;
import org.kie.api.event.rule.DebugRuleRuntimeEventListener;
import org.kie.api.logger.KieLoggers;
import org.kie.api.logger.KieRuntimeLogger;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
//@EnableWebMvc
public class LoyaltyApplication implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(LoyaltyApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(LoyaltyApplication.class, args);
	}

	private KieContainer kc;
	private KieLoggers kl;

	@Autowired
	public LoyaltyApplication(KieContainer kc, KieLoggers kl) {
		this.kc = kc;
		this.kl = kl;
	}

	@Override
	public void run(String... strings) throws Exception {
		log.info("Start Loyalty application");

		log.info("Getting KIE base");
		KieBase loyaltyKB = kc.getKieBase("LoyaltyKB");

		log.info("Getting KIE session");
		KieSession loyaltyKS = kc.newKieSession("LoyaltyKS");

//		KieRuntimeLogger klog = kl.newConsoleLogger(loyaltyKS);

		// GLOBALS
		List list= new ArrayList<Integer>();
		loyaltyKS.setGlobal("list", list);
		loyaltyKS.setGlobal("msg", "Hello");
		loyaltyKS.setGlobal("logger", LoggerFactory.getLogger("RulesProcessor"));

		// LISTENERS
//		loyaltyKS.addEventListener(new DebugAgendaEventListener());
//		loyaltyKS.addEventListener(new DebugProcessEventListener());
//		loyaltyKS.addEventListener(new DebugRuleRuntimeEventListener());

		// FACTS
		log.info("Add facts");
		loyaltyKS.insert(new ShoppingCart("alex"));

		// PROCESSING
		log.info("Run facts processing");
		loyaltyKS.fireAllRules();

		log.info("Clean up KIE session");
		loyaltyKS.dispose();
//		klog.close();
	}
}
