package com.uoa.di.csr.api.controller;

import com.uoa.di.csr.api.domain.base.ServiceRequest;
import com.uoa.di.csr.api.model.response.*;
import com.uoa.di.csr.api.repository.CitizenIdPerTotalUpvotes;
import com.uoa.di.csr.api.service.CitizenFaker;
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

    public static final String TEST_SR_TYPE = "Abandoned Vehicle Complaint";
    private final ServiceRequestService serviceRequestService;
    private final CitizenFaker citizenFaker;
    private final SessionHelper sessionHelper;

    @Autowired
    public ServiceRequestController(ServiceRequestService serviceRequestService, CitizenFaker citizenFaker, SessionHelper sessionHelper) {
        this.serviceRequestService = serviceRequestService;
        this.citizenFaker = citizenFaker;
        this.sessionHelper = sessionHelper;
    }

    @GetMapping("populate-citizens")
    public void populateCitizens() {
        citizenFaker.populateCitizens();
    }

    @GetMapping("/upvote/{serviceRequestId}")
    public boolean upvoteServiceRequest(@PathVariable("serviceRequestId") String serviceRequestId) throws Exception {
        return serviceRequestService.upvoteServiceRequest(sessionHelper.getSessionCitizen(), serviceRequestId);
    }

    @GetMapping("/search-by-type/{serviceRequestType}")
    public List<ServiceRequest> getByType(@PathVariable("serviceRequestType") String serviceRequestType) {
        return serviceRequestService.getServiceRequestsByType(serviceRequestType);
    }


    @GetMapping("/total-requests-per-type-by-date")
    public List<TotalServiceRequestsPerSrType> getServiceRequestsPerTypeByCreationDateTimeInRange(@RequestParam(name = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
                                                                                                  @RequestParam(name = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        return serviceRequestService.getServiceRequestsPerTypeByCreationDateTimeInRange(startDate, endDate);
    }

    @GetMapping("/total-requests-per-day-by-type-and-date")
    public List<TotalServiceRequestsPerCreationDay> getTotalServiceRequestsPerDayByTypeAndCreationDateTimeInRange(@RequestParam(name = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
                                                                                                                  @RequestParam(name = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        return serviceRequestService.getTotalServiceRequestsPerDayByTypeAndCreationDateTimeInRange(TEST_SR_TYPE, startDate, endDate);
    }

    @GetMapping("/three-most-common-types-per-zip-by-date")
    public List<ZipCodesPerSrType> getThreeMostCommonServiceRequestTypesPerZipCodesByCreationDate(@RequestParam(name = "creationDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate creationDate) {
        return serviceRequestService.getThreeMostCommonServiceRequestTypesPerZipCodesByCreationDate(creationDate);
    }

    @GetMapping("/three-least-common-wards-by-type")
    public List<TotalServiceRequestsPerWard> getThreeLeastCommonWardsByServiceRequestType(@RequestParam(name = "serviceRequestType", required = false) String serviceRequestType) {
        return serviceRequestService.getThreeLeastCommonWardsByServiceRequestType(TEST_SR_TYPE);
    }

    @GetMapping("/average-completion-time-per-type")
    public List<AvgCompletionTimePerSrType> getAverageCompletionTimePerServiceRequestTypeByCreationDateTimeInRange(@RequestParam(name = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
                                                                                                                   @RequestParam(name = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        return serviceRequestService.getAverageCompletionTimePerServiceRequestTypeByCreationDateTimeInRange(startDate, endDate);
    }

    @GetMapping("/most-common-type-in-bounding-box")
    public List<TotalServiceRequestsPerSrType> getMostCommonServiceRequestTypeInBoundingBox() {
        return serviceRequestService.getMostCommonServiceRequestTypeInBoundingBox(-87.6819098521912, -87.5819098521912, 41.76945357682535, 41.86945357682535);
    }

    @GetMapping("/fifty-most-upvoted-service-requests-by-date")
    public List<ServiceRequest> getFiftyMostUpvotedServiceRequestsByCreationDate(@RequestParam(name = "creationDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate creationDate) {
        return serviceRequestService.getFiftyMostUpvotedServiceRequestsByCreationDate(creationDate);
    }

    @GetMapping("/fifty-most-active-citizens")
    public List<CitizenIdPerTotalUpvotes> getFiftyMostActiveCitizens() {
        return serviceRequestService.getFiftyMostActiveCitizens();
    }


}
