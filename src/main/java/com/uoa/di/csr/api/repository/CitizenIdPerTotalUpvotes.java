package com.uoa.di.csr.api.repository;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CitizenIdPerTotalUpvotes {

    private String citizenId;
    private Integer totalUpvotes;

    public CitizenIdPerTotalUpvotes(String citizenId, int totalUpvotes) {
        this.citizenId = citizenId;
        this.totalUpvotes = totalUpvotes;
    }
}
