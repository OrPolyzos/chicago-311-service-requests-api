package com.uoa.di.csr.api.converter;

import com.uoa.di.csr.api.domain.GarbageCartRequest;
import com.uoa.di.csr.api.domain.base.ServiceRequest;
import com.uoa.di.csr.api.model.csv.GarbageCartCsv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class GarbageCartConverter implements Function<GarbageCartCsv, GarbageCartRequest> {


    private final ServiceRequestConverter serviceRequestConverter;

    @Autowired
    public GarbageCartConverter(ServiceRequestConverter serviceRequestConverter) {
        this.serviceRequestConverter = serviceRequestConverter;
    }

    @Override
    public GarbageCartRequest apply(GarbageCartCsv garbageCartCsv) {
        ServiceRequest serviceRequest = serviceRequestConverter.apply(garbageCartCsv);
        GarbageCartRequest garbageCartRequest = new GarbageCartRequest();
        serviceRequestConverter.passParentValues(serviceRequest, garbageCartRequest);
        garbageCartRequest.setNumberOfBlackCartsDelivered(serviceRequestConverter.safeParse(garbageCartCsv.getNumberOfBlackCartsDelivered(), Long::valueOf));
        serviceRequestConverter.setActivityAndSsa(garbageCartCsv, garbageCartRequest);
        return garbageCartRequest;
    }


}
