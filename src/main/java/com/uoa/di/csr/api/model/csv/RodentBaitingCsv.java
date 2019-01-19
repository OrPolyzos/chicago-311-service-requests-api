package com.uoa.di.csr.api.model.csv;

import com.opencsv.bean.CsvBindByName;
import com.uoa.di.csr.api.model.csv.base.ServiceRequestCsvWithActivityAndSsa;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RodentBaitingCsv extends ServiceRequestCsvWithActivityAndSsa {

    @CsvBindByName(column = "Number of Premises Baited")
    private String numberOfPremisesBaited;

    @CsvBindByName(column = "Number of Premises with Garbage")
    private String numberOfPremisesWithGarbage;

    @CsvBindByName(column = "Number of Premises with Rats")
    private String numberOfPremisesWithRats;

}
