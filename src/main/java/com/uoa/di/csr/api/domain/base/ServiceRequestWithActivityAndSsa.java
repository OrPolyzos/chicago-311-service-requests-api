package com.uoa.di.csr.api.domain.base;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceRequestWithActivityAndSsa extends ServiceRequest {

    @CsvBindByName(column = "current_activity")
    protected String currentActivity;

    @CsvBindByName(column = "most_recent_action")
    private String mostRecentAction;

    @CsvBindByName(column = "ssa")
    private Integer ssa;
}
