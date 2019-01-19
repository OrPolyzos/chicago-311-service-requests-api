package com.uoa.di.csr.api.domain;

import com.uoa.di.csr.api.domain.base.ServiceRequestWithActivityAndSsa;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Document(collection = "service_requests")
public class AbandonedVehicleRequest extends ServiceRequestWithActivityAndSsa {

    @Field(value = "licence_plate")
    private String licensePlate;

    @Field(value = "vehicle_make_model")
    private String vehicleMakeModel;

    @Field(value = "vehicle_color")
    private String vehicleColor;

    @Field(value = "days_reported_as_parked")
    private Long howManyDaysReportedAsParked;

}
