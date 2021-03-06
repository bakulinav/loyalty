package com.loyalty.api.client;

import com.loyalty.dto.engine.BatchProcessRequest;
import com.loyalty.dto.engine.BatchProcessResponse;
import com.loyalty.dto.engine.ProcessResponse;
import com.loyalty.dto.engine.ProcessRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.ConnectException;
import java.net.URI;

public class EngineClient {
    private static final Logger log = LoggerFactory.getLogger(EngineClient.class);

    private String engineUrl;

    public EngineClient(String engineUrl) {
        this.engineUrl = engineUrl;
    }

    public ProcessResponse processShoppingCart(ProcessRequest req) {
        log.info("Send shopping cart to processing to Engine");
        RestTemplate engineRest = new RestTemplate();

        try {
            return engineRest.postForObject(engineUrl + "/process/cart", req, ProcessResponse.class);
        } catch (RestClientException ex) {
            throw new RuntimeException("Unable to send message to Engine service", ex);
        }
    }

    public BatchProcessResponse processBatchShoppingCart(BatchProcessRequest req) {
        log.info("Send batch to processing to Engine");
        RestTemplate engineRest = new RestTemplate();

        try {
            return engineRest.postForObject(engineUrl + "/process/batch-cart", req, BatchProcessResponse.class);
        } catch (RestClientException ex) {
            throw new RuntimeException("Unable to send message to Engine service", ex);
        }
    }
}
