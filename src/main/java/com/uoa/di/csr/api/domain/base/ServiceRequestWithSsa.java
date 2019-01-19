package com.uoa.di.csr.api.domain.base;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceRequestWithSsa extends ServiceRequest {

    @CsvBindByName(column = "ssa")
    private Integer ssa;

}
