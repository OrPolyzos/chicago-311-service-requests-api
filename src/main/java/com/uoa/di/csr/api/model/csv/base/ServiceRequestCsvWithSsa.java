package com.uoa.di.csr.api.model.csv.base;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceRequestCsvWithSsa extends ServiceRequestCsv {

    @CsvBindByName(column = "SSA")
    private String ssa;

}
