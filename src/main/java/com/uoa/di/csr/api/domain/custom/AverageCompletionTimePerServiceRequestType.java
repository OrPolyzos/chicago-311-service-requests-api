package com.uoa.di.csr.api.domain.custom;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;

@Getter
@Setter
public class AverageCompletionTimePerServiceRequestType {

    @JsonIgnore
    private Double averageCompletionTimeInMillis;
    private String srType;

    private Long averageCompletionTimeInDays;

    public AverageCompletionTimePerServiceRequestType(Double averageCompletionTimeInMillis, String srType) {
        this.averageCompletionTimeInMillis = averageCompletionTimeInMillis;
        this.srType = srType;
        this.averageCompletionTimeInDays = Duration.ofMillis(averageCompletionTimeInMillis.longValue()).toDays();
    }
}
