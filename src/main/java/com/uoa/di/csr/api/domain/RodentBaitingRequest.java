package com.uoa.di.csr.api.domain;

import com.uoa.di.csr.api.domain.base.ServiceRequestWithActivityAndSsa;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Document(collection = "service_requests")
public class RodentBaitingRequest extends ServiceRequestWithActivityAndSsa {

    @Field(value = "premises_baited")
    private Long numberOfPremisesBaited;

    @Field(value = "premises_with_garbage")
    private Long numberOfPremisesWithGarbage;

    @Field(value = "premises_with_rats")
    private Long numberOfPremisesWithRats;

}
