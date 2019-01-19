package com.uoa.di.csr.api.domain.base;

import com.opencsv.bean.CsvBindByName;
import com.uoa.di.csr.api.model.csv.base.ServiceRequestCsv;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceRequestWithActivity extends ServiceRequest {

    @CsvBindByName(column = "current_activity")
    protected String currentActivity;

    @CsvBindByName(column = "most_recent_action")
    private String mostRecentAction;
}
