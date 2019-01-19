package com.uoa.di.csr.api.model.csv;

import com.opencsv.bean.CsvBindByName;
import com.uoa.di.csr.api.model.csv.base.ServiceRequestCsvWithActivityAndSsa;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AbandonedVehicleCsv extends ServiceRequestCsvWithActivityAndSsa {

    @CsvBindByName(column = "License Plate")
    private String licensePlate;

    @CsvBindByName(column = "Vehicle Make/Model")
    private String vehicleMakeModel;

    @CsvBindByName(column = "Vehicle color")
    private String vehicleColor;

    @CsvBindByName(column = "How Many Days Has the Vehicle Been Reported as Parked?")
    private String howManyDaysReportedAsParked;

}
