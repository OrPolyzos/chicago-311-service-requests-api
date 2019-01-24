package com.uoa.di.csr.api.service;

import com.uoa.di.csr.api.domain.base.ServiceRequest;
import com.uoa.di.csr.api.domain.custom.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ServiceRequestService {

    void saveServiceRequest(ServiceRequest serviceRequest);

    void saveServiceRequests(List<ServiceRequest> serviceRequests);

    List<ServiceRequest> getServiceRequestsByType(String serviceRequestType);

    List<TotalServiceRequestsPerType> getServiceRequestsPerTypeByCreationDateTimeInRange(LocalDateTime startDate, LocalDateTime endDate);

    List<TotalServiceRequestsPerDay> getTotalServiceRequestsPerDayByTypeAndCreationDateTimeInRange(String serviceRequestType, LocalDateTime startDate, LocalDateTime endDate);

    List<ServiceRequestTypePerZipCodes> getThreeMostCommonServiceRequestTypesPerZipCodesByCreationDate(LocalDate creationDate);

    List<WardPerTotalServiceRequests> getThreeLeastCommonWardsByServiceRequestType(String serviceRequestType);

    List<AverageCompletionTimePerServiceRequestType> getAverageCompletionTimePerServiceRequestTypeByCreationDateTimeInRange(LocalDateTime startDateTime, LocalDateTime endDateTime);

    List<TotalServiceRequestsPerType> getMostCommonServiceRequestTypeInBoundingBox(double x1, double x2, double y1, double y2);
}
