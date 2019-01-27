package com.uoa.di.csr.api.service;

import com.uoa.di.csr.api.domain.base.Citizen;
import com.uoa.di.csr.api.domain.base.ServiceRequest;
import com.uoa.di.csr.api.model.response.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ServiceRequestService {

    List<ServiceRequest> findAll();

    void saveServiceRequest(ServiceRequest serviceRequest);

    void saveServiceRequests(List<ServiceRequest> serviceRequests);

    ServiceRequest getById(String serviceRequestId) throws Exception;

    boolean upvoteServiceRequest(Citizen citizen, String serviceRequestId) throws Exception;

    List<ServiceRequest> getServiceRequestsByTypeLike(String serviceRequestType);

    List<TypePerTotalRequests> getTotalRequestsPerTypeByCreationDateTimeInRange(LocalDateTime startDate, LocalDateTime endDate);

    List<CreationDayPerTotalRequests> getTotalRequestsPerDayByTypeAndCreationDateTimeInRange(String serviceRequestType, LocalDateTime startDate, LocalDateTime endDate);

    List<TypePerZipCodes> getMostCommonRequestTypesPerZipCodesByCreationDate(LocalDate creationDate, Integer limit);

    List<WardPerTotalRequests> getLeastCommonWardsByType(String serviceRequestType, Integer limit);

    List<AvgTimePerType> getAvgTimePerTypeByCreationDateTimeInRange(LocalDateTime startDateTime, LocalDateTime endDateTime);

    List<TypePerTotalRequests> getMostCommonTypesInBoundingBox(double x1, double x2, double y1, double y2, Integer limit);

    List<RequestPerTotalUpvotes> getMostUpvotedRequestsByCreationDate(LocalDate creationDate, Integer limit);

    List<RequestPerSamePhoneNumbersUsed> getRequestsWithSamePhoneNumbersUsed();

}
