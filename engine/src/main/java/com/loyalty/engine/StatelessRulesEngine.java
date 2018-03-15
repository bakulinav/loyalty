package com.loyalty.engine;

import com.loyalty.engine.model.ProcessReport;
import com.loyalty.model.ShoppingCart;
import org.drools.core.event.DefaultAgendaEventListener;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.definition.rule.Rule;
import org.kie.api.event.rule.AfterMatchFiredEvent;
import org.kie.api.runtime.StatelessKieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class StatelessRulesEngine {
    private static final Logger log = LoggerFactory.getLogger(StatelessRulesEngine.class);

    private KieServices ks;

    @Autowired
    public StatelessRulesEngine(KieServices ks) {
        this.ks = ks;
    }

    /**
     * @param shoppingCart Cart to be processed
     * @param kBase Knowledge base scope of rules specific for that client
     */
    public ProcessReport run(ShoppingCart shoppingCart, KieBase kBase) {
        log.info("Cart before: Discounted=" + shoppingCart.getDiscounted());
        StatelessKieSession session = kBase.newStatelessKieSession();

        TrackingAgendaEventListener trackingListener = new TrackingAgendaEventListener();
        session.addEventListener(trackingListener);

        // init
        session.setGlobal("log", log);

        // execute
        session.execute(shoppingCart);

        log.info("Cart after: Discounted=" + shoppingCart.getDiscounted());

        List<String> fired = trackingListener.getActivationList().stream()
                .map(Activation::getRuleName)
                .collect(Collectors.toList());

        return new ProcessReport(shoppingCart, fired);
    }

    /**
     * A listener that will track all rule firings in a session.
     *
     * @see @link https://stackoverflow.com/questions/19951880/drools-how-to-find-out-which-all-rules-were-matched
     * @see @link https://github.com/gratiartis/sctrcd-payment-validation-web/blob/master/src/main/java/com/sctrcd/drools/util/TrackingAgendaEventListener.java
     * @author Stephen Masters
     */
    public class TrackingAgendaEventListener extends DefaultAgendaEventListener {

        private Logger log = LoggerFactory.getLogger(TrackingAgendaEventListener.class);

        private List<Activation> activationList = new ArrayList<Activation>();

        @Override
        public void afterMatchFired(AfterMatchFiredEvent event) {
            Rule rule = event.getMatch().getRule();

            String ruleName = rule.getName();
            Map<String, Object> ruleMetaDataMap = rule.getMetaData();

            activationList.add(new Activation(ruleName));
            StringBuilder sb = new StringBuilder("Rule fired: " + ruleName);

            if (ruleMetaDataMap.size() > 0) {
                sb.append("\n  With [" + ruleMetaDataMap.size() + "] meta-data:");
                for (String key : ruleMetaDataMap.keySet()) {
                    sb.append("\n    key=" + key + ", value="
                            + ruleMetaDataMap.get(key));
                }
            }

            log.debug(sb.toString());
        }

        public boolean isRuleFired(String ruleName) {
            for (Activation a : activationList) {
                if (a.getRuleName().equals(ruleName)) {
                    return true;
                }
            }
            return false;
        }

        public void reset() {
            activationList.clear();
        }

        public final List<Activation> getActivationList() {
            return activationList;
        }

        public String activationsToString() {
            if (activationList.size() == 0) {
                return "No activations occurred.";
            } else {
                StringBuilder sb = new StringBuilder("Activations: ");
                for (Activation activation : activationList) {
                    sb.append("\n  rule: ").append(activation.getRuleName());
                }
                return sb.toString();
            }
        }

    }

    /**
     * An activation is a record of when a rule has fired in a knowledge session.
     * Key is the name of the rule that fired, but there may also be metadata.
     *
     * @see @link https://github.com/gratiartis/sctrcd-payment-validation-web/blob/master/src/main/java/com/sctrcd/drools/util/Activation.java
     * @author Stephen Masters
     */
    public class Activation {

        /**
         * The name of the rule which was activated.
         */
        private String ruleName;

        /**
         * Default no-args constructor.
         */
        public Activation() {
        }

        /**
         * Simple constructor for just noting a rule name for an activation.
         *
         * @param ruleName
         *            The name of the rule which was activated.
         */
        public Activation(String ruleName) {
            this.ruleName = ruleName;
        }

        /**
         * @return The name of the rule which was activated.
         */
        public String getRuleName() {
            return ruleName;
        }

        /**
         * @param ruleName The name of the rule which was activated.
         */
        public void setRuleName(String ruleName) {
            this.ruleName = ruleName;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Activation[ruleName=" + ruleName);
            sb.append("]");
            return sb.toString();
        }

    }
}
