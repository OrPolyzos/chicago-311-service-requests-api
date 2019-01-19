package com.uoa.di.csr.api.model.csv;

import com.opencsv.bean.CsvBindByName;
import com.uoa.di.csr.api.model.csv.base.ServiceRequestCsvWithActivityAndSsa;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PotHoleCsv extends ServiceRequestCsvWithActivityAndSsa {

    @CsvBindByName(column = "Number of Potholes filled on block")
    private String numberOfPotholesFilledOnBlock;

}
