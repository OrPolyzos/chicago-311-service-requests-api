package com.uoa.di.csr.api.converter;

import com.uoa.di.csr.api.domain.base.ServiceRequest;
import com.uoa.di.csr.api.domain.TreeTrimsRequest;
import com.uoa.di.csr.api.model.csv.TreeTrimsCsv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class TreeTrimsConverter implements Function<TreeTrimsCsv, TreeTrimsRequest> {

    private final ServiceRequestConverter serviceRequestCsvToServiceRequest;

    @Autowired
    public TreeTrimsConverter(ServiceRequestConverter serviceRequestCsvToServiceRequest) {
        this.serviceRequestCsvToServiceRequest = serviceRequestCsvToServiceRequest;
    }

    @Override
    public TreeTrimsRequest apply(TreeTrimsCsv treeTrimsCsv) {
        ServiceRequest serviceRequest = serviceRequestCsvToServiceRequest.apply(treeTrimsCsv);
        TreeTrimsRequest treeTrimsRequest = new TreeTrimsRequest();
        serviceRequestCsvToServiceRequest.passParentValues(serviceRequest, treeTrimsRequest);
        treeTrimsRequest.setWhereAreTreesLocated(serviceRequestCsvToServiceRequest.safeParse(treeTrimsCsv.getWhereAreTreesLocated(), Function.identity()));
        return treeTrimsRequest;
    }

}
