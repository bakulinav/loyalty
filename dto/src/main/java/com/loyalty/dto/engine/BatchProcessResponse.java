package com.loyalty.dto.engine;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BatchProcessResponse {

    private Long count;
    private List<ProcessResponse> items;

    public BatchProcessResponse() {}

    public BatchProcessResponse(Long count, List<ProcessResponse> items) {
        this.count = count;
        this.items = items;
    }
}
