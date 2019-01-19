package com.uoa.di.csr.api.model.csv;

import com.opencsv.bean.CsvBindByName;
import com.uoa.di.csr.api.model.csv.base.ServiceRequestCsvWithActivityAndSsa;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GarbageCartCsv extends ServiceRequestCsvWithActivityAndSsa {

    @CsvBindByName(column = "Number of Black Carts Delivered")
    private String numberOfBlackCartsDelivered;

}
