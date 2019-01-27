package com.uoa.di.csr.api.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CreationDayPerTotalRequests {

    @JsonIgnore
    private Integer year;
    @JsonIgnore
    private Integer month;
    @JsonIgnore
    private Integer day;

    private Long totalRequests;
    private LocalDate creationDay;

    public CreationDayPerTotalRequests(Integer year, Integer month, Integer day, Long totalRequests) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.totalRequests = totalRequests;
        this.creationDay = LocalDate.of(year, month, day);
    }
}
