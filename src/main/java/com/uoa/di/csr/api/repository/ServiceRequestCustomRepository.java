package com.uoa.di.csr.api.repository;

import com.uoa.di.csr.api.domain.base.ServiceRequest;
import com.uoa.di.csr.api.model.response.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ServiceRequestCustomRepository {

    List<TotalServiceRequestsPerSrType> getTotalServiceRequestsPerTypeByCreationDateTimeInRange(LocalDateTime startDateTime, LocalDateTime endDateTime);

    List<TotalServiceRequestsPerCreationDay> getTotalServiceRequestsPerDayByTypeAndCreationDateTimeInRange(String serviceRequestType, LocalDateTime startDateTime, LocalDateTime endDateTime);

    List<ZipCodesPerSrType> getThreeMostCommonServiceRequestTypesPerZipCodesByCreationDate(LocalDate creationDate);

    List<TotalServiceRequestsPerWard> getThreeLeastCommonWardsByServiceRequestType(String serviceRequestType);

    List<AvgCompletionTimePerSrType> getAverageCompletionTimePerServiceRequestTypeByCreationDateTimeInRange(LocalDateTime startDateTime, LocalDateTime endDateTime);

    List<TotalServiceRequestsPerSrType> getMostCommonServiceRequestTypeInBoundingBox(double x1, double x2, double y1, double y2);

    List<ServiceRequest> getFiftyMostUpvotedServiceRequestsByCreationDate(LocalDate creationDate);

    List<CitizenIdPerTotalUpvotes> getFiftyMostActiveCitizens();
}
