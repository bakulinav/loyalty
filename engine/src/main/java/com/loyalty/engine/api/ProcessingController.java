package com.loyalty.engine.api;

import com.loyalty.dto.engine.BatchProcessRequest;
import com.loyalty.dto.engine.BatchProcessResponse;
import com.loyalty.dto.engine.ProcessRequest;
import com.loyalty.dto.engine.ProcessResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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


        List<ProcessResponse> items = batchRequest.getItems().stream()
                .map(rq -> new ProcessResponse(rq.getShoppingCart(), rq.getClientId(), new ArrayList<>()))
                .collect(Collectors.toList());


        return new BatchProcessResponse((long) items.size(), items);
    }
}
