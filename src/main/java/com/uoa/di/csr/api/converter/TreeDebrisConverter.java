package com.uoa.di.csr.api.converter;

import com.uoa.di.csr.api.domain.TreeDebrisRequest;
import com.uoa.di.csr.api.domain.base.ServiceRequest;
import com.uoa.di.csr.api.model.csv.TreeDebrisCsv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class TreeDebrisConverter implements Function<TreeDebrisCsv, TreeDebrisRequest> {

    private final ServiceRequestConverter serviceRequestConverter;

    @Autowired
    public TreeDebrisConverter(ServiceRequestConverter serviceRequestConverter) {
        this.serviceRequestConverter = serviceRequestConverter;
    }

    @Override
    public TreeDebrisRequest apply(TreeDebrisCsv treeDebrisCsv) {
        ServiceRequest serviceRequest = serviceRequestConverter.apply(treeDebrisCsv);
        TreeDebrisRequest treeDebrisRequest = new TreeDebrisRequest();
        serviceRequestConverter.passParentValues(serviceRequest, treeDebrisRequest);
        treeDebrisRequest.setWhereIsTheDebrisLocated(serviceRequestConverter.safeParse(treeDebrisCsv.getWhereIsDebrisLocated(), Function.identity()));
        serviceRequestConverter.setActivityAndSsa(treeDebrisCsv, treeDebrisRequest);
        return treeDebrisRequest;
    }

}
