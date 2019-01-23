package com.uoa.di.csr.api.repository;

import com.uoa.di.csr.api.domain.custom.TotalServiceRequestsPerDay;
import com.uoa.di.csr.api.domain.custom.TotalServiceRequestsPerType;

import java.time.LocalDateTime;
import java.util.List;

public interface ServiceRequestCustomRepository {

    List<TotalServiceRequestsPerType> getTotalServiceRequestsPerTypeByCreationDateTimeInRange(LocalDateTime startDateTime, LocalDateTime endDateTime);

    List<TotalServiceRequestsPerDay> getTotalServiceRequestsPerDayByTypeAndCreationDateTimeInRange(String serviceRequestType, LocalDateTime startDateTime, LocalDateTime endDateTime);
}
