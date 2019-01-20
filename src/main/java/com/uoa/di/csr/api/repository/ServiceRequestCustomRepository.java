package com.uoa.di.csr.api.repository;

import com.uoa.di.csr.api.domain.custom.TotalServiceRequestsPerType;

import java.time.LocalDateTime;
import java.util.List;

public interface ServiceRequestCustomRepository {

    List<TotalServiceRequestsPerType> getServiceRequestsPerTypeByCreationDateTimeInRange(LocalDateTime startDateTime, LocalDateTime endDateTime);
}
