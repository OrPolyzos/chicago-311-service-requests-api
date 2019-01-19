package com.uoa.di.csr.api.converter;

import com.uoa.di.csr.api.domain.AbandonedVehicleRequest;
import com.uoa.di.csr.api.domain.base.ServiceRequest;
import com.uoa.di.csr.api.model.csv.AbandonedVehicleCsv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class AbandonedVehicleConverter implements Function<AbandonedVehicleCsv, AbandonedVehicleRequest> {

    private final ServiceRequestConverter serviceRequestConverter;

    @Autowired
    public AbandonedVehicleConverter(ServiceRequestConverter serviceRequestConverter) {
        this.serviceRequestConverter = serviceRequestConverter;
    }

    @Override
    public AbandonedVehicleRequest apply(AbandonedVehicleCsv abandonedVehicleCsv) {
        ServiceRequest serviceRequest = serviceRequestConverter.apply(abandonedVehicleCsv);
        AbandonedVehicleRequest abandonedVehicleRequest = new AbandonedVehicleRequest();
        serviceRequestConverter.passParentValues(serviceRequest, abandonedVehicleRequest);
        abandonedVehicleRequest.setLicensePlate(serviceRequestConverter.safeParse(abandonedVehicleCsv.getLicensePlate(), Function.identity()));
        abandonedVehicleRequest.setVehicleMakeModel(serviceRequestConverter.safeParse(abandonedVehicleCsv.getVehicleMakeModel(), Function.identity()));
        abandonedVehicleRequest.setHowManyDaysReportedAsParked(serviceRequestConverter.safeParse(abandonedVehicleCsv.getHowManyDaysReportedAsParked(), Long::valueOf));
        abandonedVehicleRequest.setVehicleColor(serviceRequestConverter.safeParse(abandonedVehicleCsv.getVehicleColor(), Function.identity()));

        serviceRequestConverter.setActivityAndSsa(abandonedVehicleCsv, abandonedVehicleRequest);

        return abandonedVehicleRequest;
    }


}
