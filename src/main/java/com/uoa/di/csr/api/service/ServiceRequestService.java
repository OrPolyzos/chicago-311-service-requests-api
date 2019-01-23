package com.uoa.di.csr.api.service;

import com.uoa.di.csr.api.domain.base.ServiceRequest;
import com.uoa.di.csr.api.domain.custom.TotalServiceRequestsPerDay;
import com.uoa.di.csr.api.domain.custom.TotalServiceRequestsPerType;

import java.time.LocalDateTime;
import java.util.List;

public interface ServiceRequestService {

    void saveServiceRequest(ServiceRequest serviceRequest);

    void saveServiceRequests(List<ServiceRequest> serviceRequests);

    List<ServiceRequest> getServiceRequestsByType(String serviceRequestType);

    List<TotalServiceRequestsPerType> getServiceRequestsPerTypeByCreationDateTimeInRange(LocalDateTime startDate, LocalDateTime endDate);

    List<TotalServiceRequestsPerDay> getTotalServiceRequestsPerDayByTypeAndCreationDateTimeInRange(String serviceRequestType, LocalDateTime startDate, LocalDateTime endDate);

}
