package com.uoa.di.csr.api.model.csv.base;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceRequestCsv {

    @CsvBindByName(column = "Creation Date")
    protected String creationDateTime;

    @CsvBindByName(column = "Completion Date")
    protected String completionDateTime;

    @CsvBindByName(column = "Status")
    protected String status;

    @CsvBindByName(column = "Service Request Number")
    protected String serviceRequestNumber;

    @CsvBindByName(column = "Type of Service Request")
    protected String serviceRequestType;

    @CsvBindByName(column = "Street Address")
    protected String streetAddress;

    @CsvBindByName(column = "ZIP Code")
    protected String zipCode;

    @CsvBindByName(column = "X Coordinate")
    protected String coordinateX;

    @CsvBindByName(column = "Y Coordinate")
    protected String coordinateY;

    @CsvBindByName(column = "Ward")
    protected String ward;

    @CsvBindByName(column = "Police District")
    protected String policeDistrict;

    @CsvBindByName(column = "Community Area")
    protected String communityArea;

    @CsvBindByName(column = "Longitude")
    protected String longitude;

    @CsvBindByName(column = "Latitude")
    protected String latitude;

    @CsvBindByName(column = "Location")
    protected String location;

}
