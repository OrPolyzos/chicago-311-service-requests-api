package com.uoa.di.csr.api.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;

@Getter
@Setter
public class AvgCompletionTimePerSrType {

    @JsonIgnore
    private Double averageCompletionTimeInMillis;
    private String srType;

    private Long averageCompletionTimeInDays;

    public AvgCompletionTimePerSrType(Double averageCompletionTimeInMillis, String srType) {
        this.averageCompletionTimeInMillis = averageCompletionTimeInMillis;
        this.srType = srType;
        this.averageCompletionTimeInDays = Duration.ofMillis(averageCompletionTimeInMillis.longValue()).toDays();
    }
}
