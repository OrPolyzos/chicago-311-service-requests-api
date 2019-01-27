package com.uoa.di.csr.api.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WardPerTotalRequests {

    private Integer ward;
    private Long totalRequests;

    public WardPerTotalRequests(Integer ward, Long totalRequests) {
        this.ward = ward;
        this.totalRequests = totalRequests;
    }
}
