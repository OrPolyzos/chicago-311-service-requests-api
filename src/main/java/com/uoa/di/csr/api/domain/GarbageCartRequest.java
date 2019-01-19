package com.uoa.di.csr.api.domain;

import com.uoa.di.csr.api.domain.base.ServiceRequestWithActivityAndSsa;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Document(collection = "service_requests")
public class GarbageCartRequest extends ServiceRequestWithActivityAndSsa {

    @Field(value = "black_carts_delivered")
    private Long numberOfBlackCartsDelivered;

}
