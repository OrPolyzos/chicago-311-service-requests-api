package com.uoa.di.csr.api.domain.custom;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceRequestTypePerZipCodes {

    private String srType;
    private Integer zipCodes;

    public ServiceRequestTypePerZipCodes(String srType, Integer zipCodes) {
        this.srType = srType;
        this.zipCodes = zipCodes;
    }
}
