package com.uoa.di.csr.api.domain.custom;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WardPerTotalServiceRequests {

    private Integer ward;
    private Long totalServiceRequests;

    public WardPerTotalServiceRequests(Integer ward, Long totalServiceRequests) {
        this.ward = ward;
        this.totalServiceRequests = totalServiceRequests;
    }
}
