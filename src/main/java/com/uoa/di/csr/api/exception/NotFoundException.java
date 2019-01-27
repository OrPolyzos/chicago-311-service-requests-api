package com.uoa.di.csr.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    private static final String MESSAGE_TEMPLATE = "Resource %s not found";

    public NotFoundException(String id) {
        super(String.format(MESSAGE_TEMPLATE, id));
    }
}
