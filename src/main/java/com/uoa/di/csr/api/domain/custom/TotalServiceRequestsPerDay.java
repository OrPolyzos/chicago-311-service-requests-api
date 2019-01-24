package com.uoa.di.csr.api.domain.custom;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class TotalServiceRequestsPerDay {

    @JsonIgnore
    private Integer year;
    @JsonIgnore
    private Integer month;
    @JsonIgnore
    private Integer day;

    private Long totalServiceRequests;

    private LocalDate creationDay;

    public TotalServiceRequestsPerDay(Integer year, Integer month, Integer day, Long totalServiceRequests) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.totalServiceRequests = totalServiceRequests;
        this.creationDay = LocalDate.of(year, month, day);
    }
}
