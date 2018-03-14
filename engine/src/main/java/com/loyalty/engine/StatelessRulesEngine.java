package com.loyalty.engine;

import com.loyalty.model.ShoppingCart;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.runtime.StatelessKieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StatelessRulesEngine {
    private static final Logger logger = LoggerFactory.getLogger(StatelessRulesEngine.class);

    private KieServices ks;

    @Autowired
    public StatelessRulesEngine(KieServices ks) {
        this.ks = ks;
    }

    /**
     * @param shoppingCart Cart to be processed
     * @param kBase Knowledge base scope of rules specific for that client
     */
    public void run(ShoppingCart shoppingCart, KieBase kBase) {
        StatelessKieSession session = kBase.newStatelessKieSession();

        // init
        session.setGlobal("logger", logger);

        // execute
        session.execute(shoppingCart);
    }
}
