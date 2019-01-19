package com.uoa.di.csr.api.converter;

import com.uoa.di.csr.api.domain.SanitationCodeRequest;
import com.uoa.di.csr.api.domain.base.ServiceRequest;
import com.uoa.di.csr.api.model.csv.SanitationCodeCsv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class SanitationCodeConverter implements Function<SanitationCodeCsv, SanitationCodeRequest> {

    private final ServiceRequestConverter serviceRequestConverter;

    @Autowired
    public SanitationCodeConverter(ServiceRequestConverter serviceRequestConverter) {
        this.serviceRequestConverter = serviceRequestConverter;
    }

    @Override
    public SanitationCodeRequest apply(SanitationCodeCsv sanitationCodeCsv) {
        ServiceRequest serviceRequest = serviceRequestConverter.apply(sanitationCodeCsv);
        SanitationCodeRequest sanitationCodeRequest = new SanitationCodeRequest();
        serviceRequestConverter.passParentValues(serviceRequest, sanitationCodeRequest);
        sanitationCodeRequest.setNatureOfThisCodeViolation(serviceRequestConverter.safeParse(sanitationCodeCsv.getNatureOfThisCodeViolation(), Function.identity()));

        return sanitationCodeRequest;
    }

}
