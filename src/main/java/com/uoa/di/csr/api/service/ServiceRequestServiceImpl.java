package com.uoa.di.csr.api.service;

import com.uoa.di.csr.api.domain.base.Citizen;
import com.uoa.di.csr.api.domain.base.ServiceRequest;
import com.uoa.di.csr.api.model.response.*;
import com.uoa.di.csr.api.repository.CitizenIdPerTotalUpvotes;
import com.uoa.di.csr.api.repository.CitizenRepository;
import com.uoa.di.csr.api.repository.ServiceRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ServiceRequestServiceImpl implements ServiceRequestService {

    @Autowired
    private ServiceRequestRepository serviceRequestRepository;

    @Autowired
    private CitizenRepository citizenRepository;

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
        serviceRequestRepository.saveAll(serviceRequests);
    }

    @Override
    public ServiceRequest getById(String serviceRequestId) throws Exception {
        return serviceRequestRepository.findById(serviceRequestId).orElseThrow(() -> new Exception("Not found"));
    }

    @Override
    public boolean upvoteServiceRequest(Citizen citizen, String serviceRequestId) throws Exception {
        ServiceRequest serviceRequest = getById(serviceRequestId);
        if (serviceRequest.getUpvotersIds().stream().anyMatch(srCitizen -> srCitizen.getCitizenId().equals(citizen.getCitizenId()))) {
            return false;
        }
        citizen.getServiceRequestsIds().add(serviceRequest);
        serviceRequest.getUpvotersIds().add(citizen);
        citizenRepository.save(citizen);
        saveServiceRequest(serviceRequest);
        return true;
    }

    @Override
    public List<ServiceRequest> getServiceRequestsByType(String serviceRequestType) {
        return serviceRequestRepository.findAllBySrTypeContainingIgnoreCase(serviceRequestType);
    }

    @Override
    public List<TotalServiceRequestsPerSrType> getServiceRequestsPerTypeByCreationDateTimeInRange(LocalDateTime startDate, LocalDateTime endDate) {
        return serviceRequestRepository.getTotalServiceRequestsPerTypeByCreationDateTimeInRange(startDate, endDate);
    }

    @Override
    public List<TotalServiceRequestsPerCreationDay> getTotalServiceRequestsPerDayByTypeAndCreationDateTimeInRange(String serviceRequestType, LocalDateTime startDate, LocalDateTime endDate) {
        return serviceRequestRepository.getTotalServiceRequestsPerDayByTypeAndCreationDateTimeInRange(serviceRequestType, startDate, endDate);
    }

    @Override
    public List<ZipCodesPerSrType> getThreeMostCommonServiceRequestTypesPerZipCodesByCreationDate(LocalDate creationDate) {
        return serviceRequestRepository.getThreeMostCommonServiceRequestTypesPerZipCodesByCreationDate(creationDate);
    }

    @Override
    public List<TotalServiceRequestsPerWard> getThreeLeastCommonWardsByServiceRequestType(String serviceRequestType) {
        return serviceRequestRepository.getThreeLeastCommonWardsByServiceRequestType(serviceRequestType);
    }

    @Override
    public List<AvgCompletionTimePerSrType> getAverageCompletionTimePerServiceRequestTypeByCreationDateTimeInRange(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return serviceRequestRepository.getAverageCompletionTimePerServiceRequestTypeByCreationDateTimeInRange(startDateTime, endDateTime);
    }

    @Override
    public List<TotalServiceRequestsPerSrType> getMostCommonServiceRequestTypeInBoundingBox(double x1, double x2, double y1, double y2) {
        return serviceRequestRepository.getMostCommonServiceRequestTypeInBoundingBox(x1, x2, y1, y2);
    }

    @Override
    public List<ServiceRequest> getFiftyMostUpvotedServiceRequestsByCreationDate(LocalDate creationDate) {
        return serviceRequestRepository.getFiftyMostUpvotedServiceRequestsByCreationDate(creationDate);
    }

    @Override
    public List<CitizenIdPerTotalUpvotes> getFiftyMostActiveCitizens() {
        return serviceRequestRepository.getFiftyMostActiveCitizens();
    }

}
