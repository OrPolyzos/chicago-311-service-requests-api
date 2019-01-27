package com.uoa.di.csr.api.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;

@Getter
@Setter
public class AvgTimePerType {

    @JsonIgnore
    private Double averageCompletionTimeInMillis;
    private String type;

    private Long avgTimeInDays;

    public AvgTimePerType(Double averageCompletionTimeInMillis, String type) {
        this.averageCompletionTimeInMillis = averageCompletionTimeInMillis;
        this.type = type;
        this.avgTimeInDays = Duration.ofMillis(averageCompletionTimeInMillis.longValue()).toDays();
    }
}
