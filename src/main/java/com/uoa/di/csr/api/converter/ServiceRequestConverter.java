package com.uoa.di.csr.api.converter;

import com.uoa.di.csr.api.domain.base.*;
import com.uoa.di.csr.api.model.csv.base.ServiceRequestCsv;
import com.uoa.di.csr.api.model.csv.base.ServiceRequestCsvWithActivity;
import com.uoa.di.csr.api.model.csv.base.ServiceRequestCsvWithActivityAndSsa;
import com.uoa.di.csr.api.model.csv.base.ServiceRequestCsvWithSsa;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.function.Function;

@Component
public class ServiceRequestConverter implements Function<ServiceRequestCsv, ServiceRequest> {

    private static final Logger LOG = LoggerFactory.getLogger(ServiceRequestConverter.class);

    @Override
    public ServiceRequest apply(ServiceRequestCsv serviceRequestCsv) {
        ServiceRequest serviceRequest = new ServiceRequest();
        serviceRequest.setSrNumber(serviceRequestCsv.getServiceRequestNumber());
        serviceRequest.setSrType(ServiceRequestType.reverseValue(serviceRequestCsv.getServiceRequestType()));
        serviceRequest.setCreationDateTime(safeParse(serviceRequestCsv.getCreationDateTime(), LocalDateTime::parse));
        serviceRequest.setSrStatus(serviceRequestCsv.getStatus());
        serviceRequest.setStreetAddress(serviceRequestCsv.getStreetAddress());
        serviceRequest.setCompletionDateTime(safeParse(serviceRequestCsv.getCompletionDateTime(), LocalDateTime::parse));
        serviceRequest.setZipCode(safeParse(serviceRequestCsv.getZipCode(), Long::valueOf));
        serviceRequest.setCoordinateX(safeParse(serviceRequestCsv.getCoordinateX(), Double::valueOf));
        serviceRequest.setCoordinateY(safeParse(serviceRequestCsv.getCoordinateY(), Double::valueOf));
        serviceRequest.setWard(safeParse(serviceRequestCsv.getWard(), Integer::valueOf));
        serviceRequest.setPoliceDistrict(safeParse(serviceRequestCsv.getPoliceDistrict(), Integer::valueOf));
        serviceRequest.setCommunityArea(safeParse(serviceRequestCsv.getCommunityArea(), Integer::valueOf));
        serviceRequest.setLongitude(safeParse(serviceRequestCsv.getLongitude(), Double::valueOf));
        serviceRequest.setLatitude(safeParse(serviceRequestCsv.getLatitude(), Double::valueOf));
        serviceRequest.setLocation(safeParse(serviceRequestCsv.getLocation(), Function.identity()));
        return serviceRequest;
    }

    void passParentValues(ServiceRequest serviceRequest, ServiceRequest serviceRequestToPassTheValues) {
        serviceRequestToPassTheValues.setSrNumber(serviceRequest.getSrNumber());
        serviceRequestToPassTheValues.setSrType(serviceRequest.getSrType());
        serviceRequestToPassTheValues.setCreationDateTime(serviceRequest.getCreationDateTime());
        serviceRequestToPassTheValues.setSrStatus(serviceRequest.getSrStatus());
        serviceRequestToPassTheValues.setStreetAddress(serviceRequest.getStreetAddress());
        serviceRequestToPassTheValues.setCompletionDateTime(serviceRequest.getCompletionDateTime());
        serviceRequestToPassTheValues.setZipCode(serviceRequest.getZipCode());
        serviceRequestToPassTheValues.setCoordinateX(serviceRequest.getCoordinateX());
        serviceRequestToPassTheValues.setCoordinateY(serviceRequest.getCoordinateY());
        serviceRequestToPassTheValues.setWard(serviceRequest.getWard());
        serviceRequestToPassTheValues.setPoliceDistrict(serviceRequest.getPoliceDistrict());
        serviceRequestToPassTheValues.setCommunityArea(serviceRequest.getCommunityArea());
        serviceRequestToPassTheValues.setLongitude(serviceRequest.getLongitude());
        serviceRequestToPassTheValues.setLatitude(serviceRequest.getLatitude());
        serviceRequestToPassTheValues.setLocation(serviceRequest.getLocation());
    }

    void setActivityAndSsa(ServiceRequestCsvWithActivityAndSsa serviceRequestCsvWithActivityAndSsa, ServiceRequestWithActivityAndSsa serviceRequestWithActivityAndSsa) {
        serviceRequestWithActivityAndSsa.setCurrentActivity(safeParse(serviceRequestCsvWithActivityAndSsa.getCurrentActivity(), Function.identity()));
        serviceRequestWithActivityAndSsa.setMostRecentAction(safeParse(serviceRequestCsvWithActivityAndSsa.getMostRecentAction(), Function.identity()));
        serviceRequestWithActivityAndSsa.setSsa(safeParse(serviceRequestCsvWithActivityAndSsa.getSsa(), Integer::valueOf));

    }

    protected void setActivity(ServiceRequestCsvWithActivity serviceRequestCsvWithActivity, ServiceRequestWithActivity serviceRequestWithActivity) {
        serviceRequestWithActivity.setCurrentActivity(safeParse(serviceRequestCsvWithActivity.getCurrentActivity(), Function.identity()));
        serviceRequestWithActivity.setMostRecentAction(safeParse(serviceRequestCsvWithActivity.getMostRecentAction(), Function.identity()));
    }

    void setSsa(ServiceRequestCsvWithSsa serviceRequestCsvWithSsa, ServiceRequestWithSsa serviceRequestWithSsa) {
        serviceRequestWithSsa.setSsa(safeParse(serviceRequestCsvWithSsa.getSsa(), Integer::valueOf));
    }


    <T> T safeParse(String valueToParse, Function<String, T> mapper) {
        try {
            return mapToOptional(valueToParse).isPresent() ? mapper.apply(valueToParse) : null;
        } catch (Exception ex) {
            LOG.error("Failed to convert: '{}'", valueToParse);
            return null;
        }
    }

    private Optional<String> mapToOptional(String value) {
        return value == null || value.equals(Strings.EMPTY) ? Optional.empty() : Optional.of(value);
    }

}
