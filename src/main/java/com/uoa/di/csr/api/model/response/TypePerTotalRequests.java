package com.uoa.di.csr.api.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TypePerTotalRequests {

    private String type;
    private Long totalRequests;

    public TypePerTotalRequests(String type, Long totalRequests) {
        this.type = type;
        this.totalRequests = totalRequests;
    }
}
