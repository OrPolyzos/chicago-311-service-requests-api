package com.uoa.di.csr.api.controller;

import com.uoa.di.csr.api.domain.base.ServiceRequest;
import com.uoa.di.csr.api.model.response.*;
import com.uoa.di.csr.api.service.CitizenService;
import com.uoa.di.csr.api.service.ServiceRequestService;
import com.uoa.di.csr.api.service.SessionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("service-requests")
public class ServiceRequestController {

    private final ServiceRequestService serviceRequestService;
    private final CitizenService citizenService;
    private final SessionHelper sessionHelper;

    @Autowired
    public ServiceRequestController(ServiceRequestService serviceRequestService, CitizenService citizenService, SessionHelper sessionHelper) {
        this.serviceRequestService = serviceRequestService;
        this.citizenService = citizenService;
        this.sessionHelper = sessionHelper;
    }


    @PostMapping("/{serviceRequestId}/upvote")
    public boolean upvoteServiceRequest(@PathVariable("serviceRequestId") String serviceRequestId) throws Exception {
        return serviceRequestService.upvoteServiceRequest(sessionHelper.getSessionCitizen(), serviceRequestId);
    }

    @GetMapping("/type-like/{serviceRequestType}")
    public List<ServiceRequest> searchByTypeLike(@PathVariable("serviceRequestType") String serviceRequestType) {
        return serviceRequestService.getServiceRequestsByTypeLike(serviceRequestType);
    }

    @GetMapping("/per-type-by-date-in")
    public List<TypePerTotalRequests> getPerTypeByDateIn(@RequestParam(name = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
                                                         @RequestParam(name = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        return serviceRequestService.getTotalRequestsPerTypeByCreationDateTimeInRange(startDate, endDate);
    }

    @GetMapping("/per-day-by-type-and-date-in")
    public List<CreationDayPerTotalRequests> getPerDayByTypeAndDateIn(
            @RequestParam(name = "type") String srType,
            @RequestParam(name = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam(name = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        return serviceRequestService.getTotalRequestsPerDayByTypeAndCreationDateTimeInRange(srType, startDate, endDate);
    }

    @GetMapping("/most-common-types-per-zip-codes-by-date")
    public List<TypePerZipCodes> getMostCommonRequestTypesPerZipCodesByCreationDate(@RequestParam(name = "creationDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate creationDate,
                                                                                    @RequestParam(name = "limit", required = false) Integer limit) {
        return serviceRequestService.getMostCommonRequestTypesPerZipCodesByCreationDate(creationDate, limit);
    }

    @GetMapping("/least-common-wards-by-type")
    public List<WardPerTotalRequests> getLeastCommonWardsByType(@RequestParam(name = "type") String serviceRequestType,
                                                                @RequestParam(name = "limit", required = false) Integer limit) {
        return serviceRequestService.getLeastCommonWardsByType(serviceRequestType, limit);
    }

    @GetMapping("/avg-time-per-type-by-date-in")
    public List<AvgTimePerType> getAvgTimePerTypeByCreationDateTimeInRange(@RequestParam(name = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
                                                                           @RequestParam(name = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        return serviceRequestService.getAvgTimePerTypeByCreationDateTimeInRange(startDate, endDate);
    }

    @GetMapping("/most-common-types-in-bounding-box")
    public List<TypePerTotalRequests> getMostCommonTypesInBoundingBox(
            @RequestParam(name = "x1") Double x1,
            @RequestParam(name = "x2") Double x2,
            @RequestParam(name = "y1") Double y1,
            @RequestParam(name = "y2") Double y2,
            @RequestParam(name = "limit", required = false) Integer limit) {
        return serviceRequestService.getMostCommonTypesInBoundingBox(x1, x2, y1, y2, limit);
    }

    @GetMapping("/most-upvoted-service-requests-by-date")
    public List<RequestPerTotalUpvotes> getMostUpvotedServiceRequestsByCreationDate(
            @RequestParam(name = "creationDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate creationDate,
            @RequestParam(name = "limit", required = false) Integer limit) {
        return serviceRequestService.getMostUpvotedRequestsByCreationDate(creationDate, limit);
    }


    @GetMapping("/requests-with-same-phone-numbers")
    public List<RequestPerSamePhoneNumbersUsed> getRequestsWithSamePhoneNumbersUsed() {
        return serviceRequestService.getRequestsWithSamePhoneNumbersUsed();
    }
}
