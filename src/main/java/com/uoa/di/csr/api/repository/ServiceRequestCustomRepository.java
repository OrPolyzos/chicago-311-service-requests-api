package com.uoa.di.csr.api.repository;

import com.uoa.di.csr.api.domain.base.ServiceRequest;
import com.uoa.di.csr.api.model.response.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ServiceRequestCustomRepository {

    void saveAllServiceRequests(List<ServiceRequest> serviceRequests);

    List<TypePerTotalRequests> getTotalRequestsPerTypeByCreationDateTimeInRange(LocalDateTime startDateTime, LocalDateTime endDateTime);

    List<CreationDayPerTotalRequests> getTotalRequestsPerDayByTypeAndCreationDateTimeInRange(String serviceRequestType, LocalDateTime startDateTime, LocalDateTime endDateTime);

    List<TypePerZipCodes> getMostCommonRequestTypesPerZipCodesByCreationDate(LocalDate creationDate, Integer limit);

    List<WardPerTotalRequests> getLeastCommonWardsByType(String serviceRequestType, Integer limit);

    List<AvgTimePerType> getAvgTimePerTypeByCreationDateTimeInRange(LocalDateTime startDateTime, LocalDateTime endDateTime);

    List<TypePerTotalRequests> getMostCommonTypesInBoundingBox(double x1, double x2, double y1, double y2, Integer limit);

    List<RequestPerTotalUpvotes> getMostUpvotedRequestsByCreationDate(LocalDate creationDate, Integer limit);

    List<RequestPerSamePhoneNumbersUsed> getRequestsWithSamePhoneNumbersUsed();
}