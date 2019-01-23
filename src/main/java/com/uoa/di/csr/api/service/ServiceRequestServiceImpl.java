package com.uoa.di.csr.api.service;

import com.uoa.di.csr.api.domain.base.ServiceRequest;
import com.uoa.di.csr.api.domain.custom.TotalServiceRequestsPerDay;
import com.uoa.di.csr.api.domain.custom.TotalServiceRequestsPerType;
import com.uoa.di.csr.api.repository.ServiceRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ServiceRequestServiceImpl implements ServiceRequestService {

    @Autowired
    private ServiceRequestRepository serviceRequestRepository;

    @Override
    public void saveServiceRequest(ServiceRequest serviceRequest) {
        serviceRequestRepository.save(serviceRequest);
    }

    @Override
    public void saveServiceRequests(List<ServiceRequest> serviceRequests) {
        serviceRequestRepository.saveAll(serviceRequests);
    }

    @Override
    public List<ServiceRequest> getServiceRequestsByType(String serviceRequestType) {
        return serviceRequestRepository.findAllBySrTypeContainingIgnoreCase(serviceRequestType);
    }

    @Override
    public List<TotalServiceRequestsPerType> getServiceRequestsPerTypeByCreationDateTimeInRange(LocalDateTime startDate, LocalDateTime endDate) {
        return serviceRequestRepository.getTotalServiceRequestsPerTypeByCreationDateTimeInRange(startDate, endDate);
    }

    @Override
    public List<TotalServiceRequestsPerDay> getTotalServiceRequestsPerDayByTypeAndCreationDateTimeInRange(String serviceRequestType, LocalDateTime startDate, LocalDateTime endDate) {
        return serviceRequestRepository.getTotalServiceRequestsPerDayByTypeAndCreationDateTimeInRange(serviceRequestType, startDate, endDate);
    }


}
