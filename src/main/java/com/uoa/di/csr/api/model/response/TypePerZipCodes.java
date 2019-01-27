package com.uoa.di.csr.api.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TypePerZipCodes {

    private String type;
    private Integer zipCodes;

    public TypePerZipCodes(String type, Integer zipCodes) {
        this.type = type;
        this.zipCodes = zipCodes;
    }
}
