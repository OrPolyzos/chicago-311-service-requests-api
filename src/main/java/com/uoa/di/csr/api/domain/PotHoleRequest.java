package com.uoa.di.csr.api.domain;

import com.uoa.di.csr.api.domain.base.ServiceRequestWithActivityAndSsa;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Document(collection = "service_requests")
public class PotHoleRequest extends ServiceRequestWithActivityAndSsa {

    @Field(value = "pot_holes_filled_on_block")
    private Long numberOfPotholesFilledOnBlock;

}
