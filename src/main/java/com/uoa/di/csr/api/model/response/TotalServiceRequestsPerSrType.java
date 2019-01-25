package com.uoa.di.csr.api.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TotalServiceRequestsPerSrType {

    private String srType;
    private Long totalServiceRequests;

    public TotalServiceRequestsPerSrType(String srType, Long totalServiceRequests) {
        this.srType = srType;
        this.totalServiceRequests = totalServiceRequests;
    }
}
