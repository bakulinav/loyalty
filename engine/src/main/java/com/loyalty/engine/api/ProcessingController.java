package com.loyalty.engine.api;

import com.loyalty.dto.engine.BatchProcessRequest;
import com.loyalty.dto.engine.BatchProcessResponse;
import com.loyalty.dto.engine.ProcessRequest;
import com.loyalty.dto.engine.ProcessResponse;
import com.loyalty.engine.service.ShoppingCartProcessor;
import com.loyalty.engine.model.ProcessReport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/process")
public class ProcessingController {
    private static final Logger log = LoggerFactory.getLogger(ProcessingController.class);

    private ShoppingCartProcessor scProcessor;

    @Autowired
    public ProcessingController(ShoppingCartProcessor scProcessor) {
        this.scProcessor = scProcessor;
    }

    @PostMapping("/cart")
    @ResponseBody
    public ProcessResponse processCart(@RequestBody ProcessRequest eventRequest) {
        log.info("Request to Shopping cart processing: " + eventRequest);

        ProcessReport report = scProcessor.process(
                eventRequest.getState(),
                eventRequest.getShoppingCart(),
                eventRequest.getClientId());// TODO: take from Session over authorisation

        return new ProcessResponse(
                report.getSubject(),
                eventRequest.getClientId(),
                report.getFiredRules());
    }

    @PostMapping("/batch-cart")
    @ResponseBody
    public BatchProcessResponse processBatchCart(@RequestBody BatchProcessRequest batchRequest) {
        log.info("Request to Batch Shopping cart processing");

        // TODO: find the way process batch of requires and not iterate over them
        List<ProcessResponse> items = batchRequest.getItems().stream()
                .map(rq -> {
                    ProcessReport report = scProcessor.process(
                            rq.getState(),
                            rq.getShoppingCart(),
                            rq.getClientId());// TODO: take from Session over authorisation

                    return new ProcessResponse(
                            report.getSubject(),
                            rq.getClientId(),
                            report.getFiredRules());
                })
                .collect(Collectors.toList());

        return new BatchProcessResponse((long) items.size(), items);
    }
}
