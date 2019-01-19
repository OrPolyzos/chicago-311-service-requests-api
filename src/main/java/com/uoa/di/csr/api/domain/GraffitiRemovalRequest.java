package com.uoa.di.csr.api.domain;

import com.uoa.di.csr.api.domain.base.ServiceRequestWithSsa;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Document(collection = "service_requests")
public class GraffitiRemovalRequest extends ServiceRequestWithSsa {

    @Field(value = "type_of_surface")
    private String whatTypeOfSurfaceTheGraffitiIsOn;

    @Field(value = "where_is_located")
    private String whereIsTheGraffitiLocated;

}
