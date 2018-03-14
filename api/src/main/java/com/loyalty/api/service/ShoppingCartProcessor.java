package com.loyalty.api.service;

import com.loyalty.api.client.EngineClient;
import com.loyalty.dto.api.BatchShoppingCart;
import com.loyalty.dto.engine.BatchProcessRequest;
import com.loyalty.dto.engine.BatchProcessResponse;
import com.loyalty.dto.engine.ProcessRequest;
import com.loyalty.dto.engine.ProcessResponse;
import com.loyalty.model.EngineMode;
import com.loyalty.model.ShoppingCart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class ShoppingCartProcessor {

    private static final Logger log = LoggerFactory.getLogger(ShoppingCartProcessor.class);

    public EngineClient engineClient;

    public ShoppingCartProcessor(EngineClient engineClient) {
        this.engineClient = engineClient;
    }

    public ProcessResponse process(ShoppingCart shoppingCart) {
        log.debug("Get Shopping cart for processing");

        ProcessResponse result = engineClient.processShoppingCart(
                new ProcessRequest(shoppingCart, "test_client_1", EngineMode.STATELESS)
        );

        log.debug("Shopping cart has been processed");

        return result;
    }

    public BatchProcessResponse processBatch(BatchShoppingCart batch) {
        log.debug("Get Batch Shopping cart for processing");

        List<ProcessRequest> items = batch.getItems().stream()
                .map(sc -> new ProcessRequest(sc, "test_client_1", EngineMode.STATELESS))
                .collect(Collectors.toList());

        BatchProcessRequest req = new BatchProcessRequest((long) items.size(), items);

        BatchProcessResponse result = engineClient.processBatchShoppingCart(req);

        log.info("Shopping cart Batch has been processed");

        return result;
    }
}
