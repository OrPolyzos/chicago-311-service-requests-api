package com.uoa.di.csr.api.service;

import com.uoa.di.csr.api.domain.base.ServiceRequest;
import com.uoa.di.csr.api.domain.custom.TotalServiceRequestsPerType;

import java.time.LocalDateTime;
import java.util.List;

public interface ServiceRequestService {

    void saveServiceRequest(ServiceRequest serviceRequest);

    List<ServiceRequest> getServiceRequestsByType(String serviceRequestType);

    List<TotalServiceRequestsPerType> getServiceRequestsPerTypeByCreationDateTimeInRange(LocalDateTime startDate, LocalDateTime endDate);
}
