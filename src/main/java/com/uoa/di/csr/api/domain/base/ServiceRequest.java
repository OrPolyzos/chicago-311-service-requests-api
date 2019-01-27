package com.uoa.di.csr.api.domain.base;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Document(collection = "service_requests")
public class ServiceRequest {

    //TODO CUSTOM VALIDATIONS SHOULD BE ADDED

    @Id
    @Field(value = "service_request_id")
    protected String srId;

    @Field(value = "creation_date_time")
    @NotNull(message = "Creation DateTime cannot be null and must be in ISO_DATE_TIME format")
    protected LocalDateTime creationDateTime;

    @Field(value = "completion_date_time")
    protected LocalDateTime completionDateTime;

    @Field(value = "sr_number")
    protected String srNumber;

    @Field(value = "sr_type")
    protected String srType;

    @Field(value = "sr_status")
    protected String srStatus;

    @Field(value = "street_address")
    protected String streetAddress;

    @Field(value = "zip_code")
    protected Long zipCode;

    @Field(value = "coordinate_x")
    protected Double coordinateX;

    @Field(value = "coordinate_y")
    protected Double coordinateY;

    @Field(value = "ward")
    protected Integer ward;

    @Field(value = "police_district")
    protected Integer policeDistrict;

    @Field(value = "community_area")
    protected Integer communityArea;

    @Field(value = "geo_location")
    @GeoSpatialIndexed(name = "geo_location_index", type = GeoSpatialIndexType.GEO_2DSPHERE)
    protected GeoJsonPoint geoLocation;

    @Field(value = "upvoters_ids")
    protected Set<String> upvotersIds = new HashSet<>();

    @Field(value = "upvoters_telephone_numbers")
    protected Set<String> upvotersTelephoneNumbers = new HashSet<>();
}
