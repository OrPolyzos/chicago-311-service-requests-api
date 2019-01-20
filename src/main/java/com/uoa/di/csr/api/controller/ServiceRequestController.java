package com.uoa.di.csr.api.controller;

import com.uoa.di.csr.api.domain.base.ServiceRequest;
import com.uoa.di.csr.api.domain.custom.TotalServiceRequestsPerType;
import com.uoa.di.csr.api.service.ServiceRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("service-requests")
public class ServiceRequestController {

    private final ServiceRequestService serviceRequestService;

    @Autowired
    public ServiceRequestController(ServiceRequestService serviceRequestService) {
        this.serviceRequestService = serviceRequestService;
    }

    @GetMapping("/search-by-type/{serviceRequestType}")
    public List<ServiceRequest> getByType(@PathVariable("serviceRequestType") String serviceRequestType) {
        return serviceRequestService.getServiceRequestsByType(serviceRequestType);
    }


    @GetMapping("/total-requests-per-type")
    public List<TotalServiceRequestsPerType> getByType(@RequestParam(name = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
                                                       @RequestParam(name = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        return serviceRequestService.getServiceRequestsPerTypeByCreationDateTimeInRange(startDate, endDate);
    }
}
