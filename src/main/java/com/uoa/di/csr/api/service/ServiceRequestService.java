package com.uoa.di.csr.api.service;

import com.uoa.di.csr.api.domain.base.Citizen;
import com.uoa.di.csr.api.domain.base.ServiceRequest;
import com.uoa.di.csr.api.model.response.*;
import com.uoa.di.csr.api.repository.CitizenIdPerTotalUpvotes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ServiceRequestService {

    List<ServiceRequest> findAll();

    void saveServiceRequest(ServiceRequest serviceRequest);

    void saveServiceRequests(List<ServiceRequest> serviceRequests);

    ServiceRequest getById(String serviceRequestId) throws Exception;

    boolean upvoteServiceRequest(Citizen citizen, String serviceRequestId) throws Exception;

    List<ServiceRequest> getServiceRequestsByType(String serviceRequestType);

    List<TotalServiceRequestsPerSrType> getServiceRequestsPerTypeByCreationDateTimeInRange(LocalDateTime startDate, LocalDateTime endDate);

    List<TotalServiceRequestsPerCreationDay> getTotalServiceRequestsPerDayByTypeAndCreationDateTimeInRange(String serviceRequestType, LocalDateTime startDate, LocalDateTime endDate);

    List<ZipCodesPerSrType> getThreeMostCommonServiceRequestTypesPerZipCodesByCreationDate(LocalDate creationDate);

    List<TotalServiceRequestsPerWard> getThreeLeastCommonWardsByServiceRequestType(String serviceRequestType);

    List<AvgCompletionTimePerSrType> getAverageCompletionTimePerServiceRequestTypeByCreationDateTimeInRange(LocalDateTime startDateTime, LocalDateTime endDateTime);

    List<TotalServiceRequestsPerSrType> getMostCommonServiceRequestTypeInBoundingBox(double x1, double x2, double y1, double y2);

    List<ServiceRequest> getFiftyMostUpvotedServiceRequestsByCreationDate(LocalDate creationDate);

    List<CitizenIdPerTotalUpvotes> getFiftyMostActiveCitizens();
}
