package com.loyalty.dto.engine;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BatchProcessRequest {

    private Long count;
    private List<ProcessRequest> items;

    public BatchProcessRequest() {}

    public BatchProcessRequest(Long count, List<ProcessRequest> items) {
        this.count = count;
        this.items = items;
    }
}
