package com.uoa.di.csr.api.domain.custom;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TotalServiceRequestsPerType {

    private String srType;
    private Long totalServiceRequests;

    public TotalServiceRequestsPerType(String srType, Long totalServiceRequests) {
        this.srType = srType;
        this.totalServiceRequests = totalServiceRequests;
    }
}
