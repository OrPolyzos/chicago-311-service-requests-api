package com.uoa.di.csr.api.repository;

import com.uoa.di.csr.api.domain.base.ServiceRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.time.LocalDateTime;
import java.util.List;

@RepositoryRestResource(collectionResourceRel = "service_requests", path = "service-requests")
public interface ServiceRequestRepository extends MongoRepository<ServiceRequest, String>, ServiceRequestCustomRepository {

    List<ServiceRequest> findAllBySrTypeContainingIgnoreCase(String serviceRequestType);

}
