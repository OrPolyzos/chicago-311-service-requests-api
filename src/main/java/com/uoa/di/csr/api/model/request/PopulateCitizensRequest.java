package com.uoa.di.csr.api.model.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PopulateCitizensRequest {

    @NotNull(message = "maxUpvotes cannot be null.")
    private Integer maxUpvotes;

}
