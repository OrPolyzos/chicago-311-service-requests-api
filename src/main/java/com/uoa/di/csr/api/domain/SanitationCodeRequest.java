package com.uoa.di.csr.api.domain;

import com.uoa.di.csr.api.domain.base.ServiceRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Document(collection = "service_requests")
public class SanitationCodeRequest extends ServiceRequest {

    @Field(value = "nature_of_violation")
    private String natureOfThisCodeViolation;
}
