package com.uoa.di.csr.api.converter;

import com.uoa.di.csr.api.domain.GraffitiRemovalRequest;
import com.uoa.di.csr.api.domain.base.ServiceRequest;
import com.uoa.di.csr.api.model.csv.GraffitiRemovalCsv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class GraffitiRemovalConverter implements Function<GraffitiRemovalCsv, GraffitiRemovalRequest> {

    @Autowired
    private ServiceRequestConverter serviceRequestConverter;

    @Override
    public GraffitiRemovalRequest apply(GraffitiRemovalCsv graffitiRemovalCsv) {
        ServiceRequest serviceRequest = serviceRequestConverter.apply(graffitiRemovalCsv);
        GraffitiRemovalRequest graffitiRemovalRequest = new GraffitiRemovalRequest();
        serviceRequestConverter.passParentValues(serviceRequest, graffitiRemovalRequest);
        graffitiRemovalRequest.setWhatTypeOfSurfaceTheGraffitiIsOn(serviceRequestConverter.safeParse(graffitiRemovalCsv.getWhatTypeOfSurfaceTheGraffitiIsOn(), Function.identity()));
        graffitiRemovalRequest.setWhereIsTheGraffitiLocated(serviceRequestConverter.safeParse(graffitiRemovalCsv.getWhereIsTheGraffitiLocated(), Function.identity()));

        serviceRequestConverter.setSsa(graffitiRemovalCsv, graffitiRemovalRequest);
        return graffitiRemovalRequest;
    }

}
