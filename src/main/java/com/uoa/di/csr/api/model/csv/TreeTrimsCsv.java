package com.uoa.di.csr.api.model.csv;

import com.opencsv.bean.CsvBindByName;
import com.uoa.di.csr.api.model.csv.base.ServiceRequestCsv;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TreeTrimsCsv extends ServiceRequestCsv {

    @CsvBindByName(column = "Location of Trees")
    private String whereAreTreesLocated;

}
