package com.uoa.di.csr.api.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestPerSamePhoneNumbersUsed {

    private String id;
    private Integer samePhoneNumbersUsed;

    public RequestPerSamePhoneNumbersUsed(String id, Integer samePhoneNumbersUsed) {
        this.id = id;
        this.samePhoneNumbersUsed = samePhoneNumbersUsed;
    }
}
