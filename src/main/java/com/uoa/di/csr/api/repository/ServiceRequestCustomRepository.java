package com.uoa.di.csr.api.repository;

import com.uoa.di.csr.api.domain.custom.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ServiceRequestCustomRepository {

    List<TotalServiceRequestsPerType> getTotalServiceRequestsPerTypeByCreationDateTimeInRange(LocalDateTime startDateTime, LocalDateTime endDateTime);

    List<TotalServiceRequestsPerDay> getTotalServiceRequestsPerDayByTypeAndCreationDateTimeInRange(String serviceRequestType, LocalDateTime startDateTime, LocalDateTime endDateTime);

    List<ServiceRequestTypePerZipCodes> getThreeMostCommonServiceRequestTypesPerZipCodesByCreationDate(LocalDate creationDate);

    List<WardPerTotalServiceRequests> getThreeLeastCommonWardsByServiceRequestType(String serviceRequestType);

    List<AverageCompletionTimePerServiceRequestType> getAverageCompletionTimePerServiceRequestTypeByCreationDateTimeInRange(LocalDateTime startDateTime, LocalDateTime endDateTime);

}
