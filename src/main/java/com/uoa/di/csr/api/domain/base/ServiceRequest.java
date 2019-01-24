package com.uoa.di.csr.api.domain.base;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Getter
@Setter
@Document(collection = "service_requests")
public class ServiceRequest {

    @Id
    @Field(value = "service_request_id")
    protected String srId;

    @Field(value = "creation_date_time")
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
    protected GeoJsonPoint geoLocation;


}
