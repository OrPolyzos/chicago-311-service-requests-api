package com.uoa.di.csr.api.converter;

import com.uoa.di.csr.api.domain.RodentBaitingRequest;
import com.uoa.di.csr.api.domain.base.ServiceRequest;
import com.uoa.di.csr.api.model.csv.RodentBaitingCsv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class RodentBaitingConverter implements Function<RodentBaitingCsv, RodentBaitingRequest> {

    private final ServiceRequestConverter serviceRequestConverter;

    @Autowired
    public RodentBaitingConverter(ServiceRequestConverter serviceRequestConverter) {
        this.serviceRequestConverter = serviceRequestConverter;
    }

    @Override
    public RodentBaitingRequest apply(RodentBaitingCsv rodentBaitingCsv) {
        ServiceRequest serviceRequest = serviceRequestConverter.apply(rodentBaitingCsv);
        RodentBaitingRequest rodentBaiting = new RodentBaitingRequest();
        serviceRequestConverter.passParentValues(serviceRequest, rodentBaiting);
        rodentBaiting.setNumberOfPremisesBaited(serviceRequestConverter.safeParse(rodentBaitingCsv.getNumberOfPremisesBaited(), Long::valueOf));
        rodentBaiting.setNumberOfPremisesWithGarbage(serviceRequestConverter.safeParse(rodentBaitingCsv.getNumberOfPremisesWithGarbage(), Long::valueOf));
        rodentBaiting.setNumberOfPremisesWithRats(serviceRequestConverter.safeParse(rodentBaitingCsv.getNumberOfPremisesWithRats(), Long::valueOf));

        rodentBaiting.setSsa(serviceRequestConverter.safeParse(rodentBaitingCsv.getSsa(), Integer::valueOf));
        rodentBaiting.setCurrentActivity(serviceRequestConverter.safeParse(rodentBaitingCsv.getCurrentActivity(), Function.identity()));
        rodentBaitingCsv.setMostRecentAction(serviceRequestConverter.safeParse(rodentBaitingCsv.getMostRecentAction(), Function.identity()));

        return rodentBaiting;
    }

}
