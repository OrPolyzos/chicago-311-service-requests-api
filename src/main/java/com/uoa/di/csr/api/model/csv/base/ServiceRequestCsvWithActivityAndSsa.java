package com.uoa.di.csr.api.model.csv.base;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceRequestCsvWithActivityAndSsa extends ServiceRequestCsv {

    @CsvBindByName(column = "Current Activity")
    protected String currentActivity;

    @CsvBindByName(column = "Most Recent Action")
    private String mostRecentAction;

    @CsvBindByName(column = "SSA")
    private String ssa;
}
