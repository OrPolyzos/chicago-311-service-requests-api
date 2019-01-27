package com.uoa.di.csr.api.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CitizenPerTotalUpvotes {

    private String id;
    private Integer totalUpvotes;

    public CitizenPerTotalUpvotes(String id, int totalUpvotes) {
        this.id = id;
        this.totalUpvotes = totalUpvotes;
    }
}
