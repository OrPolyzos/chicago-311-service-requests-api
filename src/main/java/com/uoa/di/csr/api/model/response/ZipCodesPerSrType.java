package com.uoa.di.csr.api.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ZipCodesPerSrType {

    private String srType;
    private Integer zipCodes;

    public ZipCodesPerSrType(String srType, Integer zipCodes) {
        this.srType = srType;
        this.zipCodes = zipCodes;
    }
}
