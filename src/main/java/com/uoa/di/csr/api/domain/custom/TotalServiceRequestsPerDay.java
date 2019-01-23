package com.uoa.di.csr.api.domain.custom;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TotalServiceRequestsPerDay {

    private LocalDate creationDay;
    private Integer year;
    private Integer month;
    private Integer day;
    private Long totalServiceRequests;

    public TotalServiceRequestsPerDay(Integer year, Integer month, Integer day, Long totalServiceRequests) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.totalServiceRequests = totalServiceRequests;
        this.creationDay = LocalDate.of(year, month, day);
    }
}
