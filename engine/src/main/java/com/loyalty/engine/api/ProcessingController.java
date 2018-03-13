package com.loyalty.engine.api;

import com.loyalty.engine.dto.BatchProcessRequest;
import com.loyalty.engine.dto.BatchProcessResponse;
import com.loyalty.engine.dto.ProcessRequest;
import com.loyalty.engine.dto.ProcessResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
@RequestMapping("/process")
public class ProcessingController {
    private static final Logger log = LoggerFactory.getLogger(ProcessingController.class);

    @PostMapping("/cart")
    @ResponseBody
    public ProcessResponse processCart(@RequestBody ProcessRequest eventRequest) {
        log.info("Request to Shopping cart processing: " + eventRequest);

        //TODO: send cart to processing

        return new ProcessResponse(
                eventRequest.getShoppingCart(),
                eventRequest.getClientId(),
                new ArrayList<>());
    }

    @PostMapping("/batch-cart")
    @ResponseBody
    public BatchProcessResponse processBatchCart(@RequestBody BatchProcessRequest batchRequest) {
        log.info("Request to Batch Shopping cart processing");

        //TODO: send batch to processing

        return new BatchProcessResponse();
    }
}
