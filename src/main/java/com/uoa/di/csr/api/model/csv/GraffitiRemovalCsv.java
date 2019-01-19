package com.uoa.di.csr.api.model.csv;

import com.opencsv.bean.CsvBindByName;
import com.uoa.di.csr.api.model.csv.base.ServiceRequestCsvWithSsa;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GraffitiRemovalCsv extends ServiceRequestCsvWithSsa {

    @CsvBindByName(column = "What Type of Surface is the Graffiti on?")
    private String whatTypeOfSurfaceTheGraffitiIsOn;

    @CsvBindByName(column = "Where is the Graffiti located?")
    private String whereIsTheGraffitiLocated;

}
