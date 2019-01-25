package com.uoa.di.csr.api.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TotalServiceRequestsPerWard {

    private Integer ward;
    private Long totalServiceRequests;

    public TotalServiceRequestsPerWard(Integer ward, Long totalServiceRequests) {
        this.ward = ward;
        this.totalServiceRequests = totalServiceRequests;
    }
}
