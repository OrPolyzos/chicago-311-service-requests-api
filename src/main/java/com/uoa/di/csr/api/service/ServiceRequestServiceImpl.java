package com.uoa.di.csr.api.service;

import com.uoa.di.csr.api.domain.base.Citizen;
import com.uoa.di.csr.api.domain.base.ServiceRequest;
import com.uoa.di.csr.api.exception.NotFoundException;
import com.uoa.di.csr.api.model.response.*;
import com.uoa.di.csr.api.repository.ServiceRequestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ServiceRequestServiceImpl implements ServiceRequestService {

    private static final Logger LOG = LoggerFactory.getLogger(ServiceRequestServiceImpl.class);

    @Autowired
    private ServiceRequestRepository serviceRequestRepository;

    @Autowired
    private CitizenService citizenService;

    @Override
    public List<ServiceRequest> findAll() {
        return serviceRequestRepository.findAll();
    }

    @Override
    public void saveServiceRequest(ServiceRequest serviceRequest) {
        serviceRequestRepository.save(serviceRequest);
    }

    @Override
    public void saveServiceRequests(List<ServiceRequest> serviceRequests) {
        LOG.info("Started saving service requests...");
        serviceRequestRepository.saveAllServiceRequests(serviceRequests);
        LOG.info("Successfully saved service requests.");
    }

    @Override
    public ServiceRequest getById(String serviceRequestId) {
        return serviceRequestRepository.findById(serviceRequestId).orElseThrow(() -> new NotFoundException(serviceRequestId));
    }

    @Override
    public boolean upvoteServiceRequest(Citizen citizen, String serviceRequestId) {
        ServiceRequest serviceRequest = getById(serviceRequestId);
        if (serviceRequest.getUpvotersIds().contains(citizen.getCitizenId())) {
            return false;
        }
        citizen.getUpvotedSrIds().add(serviceRequestId);
        citizen.getUpvotedWards().add(serviceRequest.getWard());
        serviceRequest.getUpvotersIds().add(citizen.getCitizenId());
        serviceRequest.getUpvotersTelephoneNumbers().add(citizen.getTelephoneNumber());
        citizenService.saveCitizen(citizen);
        saveServiceRequest(serviceRequest);
        return true;
    }

    @Override
    public List<ServiceRequest> getServiceRequestsByTypeLike(String serviceRequestType) {
        return serviceRequestRepository.findAllBySrTypeContainingIgnoreCase(serviceRequestType);
    }

    @Override
    public List<TypePerTotalRequests> getTotalRequestsPerTypeByCreationDateTimeInRange(LocalDateTime startDate, LocalDateTime endDate) {
        return serviceRequestRepository.getTotalRequestsPerTypeByCreationDateTimeInRange(startDate, endDate);
    }

    @Override
    public List<CreationDayPerTotalRequests> getTotalRequestsPerDayByTypeAndCreationDateTimeInRange(String serviceRequestType, LocalDateTime startDate, LocalDateTime endDate) {
        return serviceRequestRepository.getTotalRequestsPerDayByTypeAndCreationDateTimeInRange(serviceRequestType, startDate, endDate);
    }

    @Override
    public List<TypePerZipCodes> getMostCommonRequestTypesPerZipCodesByCreationDate(LocalDate creationDate, Integer limit) {
        //TODO Should be moved to application properties as default value
        if (limit == null) limit = 3;
        return serviceRequestRepository.getMostCommonRequestTypesPerZipCodesByCreationDate(creationDate, limit);
    }

    @Override
    public List<WardPerTotalRequests> getLeastCommonWardsByType(String serviceRequestType, Integer limit) {
        //TODO Should be moved to application properties as default value
        if (limit == null) limit = 3;
        return serviceRequestRepository.getLeastCommonWardsByType(serviceRequestType, limit);
    }

    @Override
    public List<AvgTimePerType> getAvgTimePerTypeByCreationDateTimeInRange(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return serviceRequestRepository.getAvgTimePerTypeByCreationDateTimeInRange(startDateTime, endDateTime);
    }

    @Override
    public List<TypePerTotalRequests> getMostCommonTypesInBoundingBox(double x1, double x2, double y1, double y2, Integer limit) {
        //TODO Should be moved to application properties as default value
        if (limit == null) limit = 1;
        return serviceRequestRepository.getMostCommonTypesInBoundingBox(x1, x2, y1, y2, limit);
    }

    @Override
    public List<RequestPerTotalUpvotes> getMostUpvotedRequestsByCreationDate(LocalDate creationDate, Integer limit) {
        //TODO Should be moved to application properties as default value
        if (limit == null) limit = 50;
        return serviceRequestRepository.getMostUpvotedRequestsByCreationDate(creationDate, limit);
    }

    @Override
    public List<RequestPerSamePhoneNumbersUsed> getRequestsWithSamePhoneNumbersUsed() {
        return serviceRequestRepository.getRequestsWithSamePhoneNumbersUsed();
    }

}
