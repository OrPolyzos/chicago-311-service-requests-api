package com.uoa.di.csr.api.converter;

import com.uoa.di.csr.api.domain.PotHoleRequest;
import com.uoa.di.csr.api.domain.base.ServiceRequest;
import com.uoa.di.csr.api.model.csv.PotHoleCsv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class PotHolesConverter implements Function<PotHoleCsv, PotHoleRequest> {

    private final ServiceRequestConverter serviceRequestConverter;

    @Autowired
    public PotHolesConverter(ServiceRequestConverter serviceRequestConverter) {
        this.serviceRequestConverter = serviceRequestConverter;
    }

    @Override
    public PotHoleRequest apply(PotHoleCsv potHoleCsv) {
        ServiceRequest serviceRequest = serviceRequestConverter.apply(potHoleCsv);
        PotHoleRequest potHoleRequest = new PotHoleRequest();
        serviceRequestConverter.passParentValues(serviceRequest, potHoleRequest);
        potHoleRequest.setNumberOfPotholesFilledOnBlock(serviceRequestConverter.safeParse(potHoleCsv.getNumberOfPotholesFilledOnBlock(), Long::valueOf));

        serviceRequestConverter.setActivityAndSsa(potHoleCsv, potHoleRequest);
        return potHoleRequest;
    }

}
