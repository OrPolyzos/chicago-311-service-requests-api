package com.uoa.di.csr.api.model.csv;

import com.opencsv.bean.CsvBindByName;
import com.uoa.di.csr.api.model.csv.base.ServiceRequestCsvWithActivityAndSsa;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TreeDebrisCsv extends ServiceRequestCsvWithActivityAndSsa {

    @CsvBindByName(column = "If Yes, where is the debris located?")
    private String whereIsDebrisLocated;

    @CsvBindByName(column = "SSA")
    private String ssa;

    @CsvBindByName(column = "Current Activity")
    private String currentActivity;

    @CsvBindByName(column = "Most Recent Action")
    private String mostRecentAction;

}
