package com.uoa.di.csr.api.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestPerTotalUpvotes {

    private String id;
    private Integer totalUpvotes;

    public RequestPerTotalUpvotes(String id, int totalUpvotes) {
        this.id = id;
        this.totalUpvotes = totalUpvotes;
    }
}
