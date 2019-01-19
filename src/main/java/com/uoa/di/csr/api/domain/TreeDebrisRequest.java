package com.uoa.di.csr.api.domain;


import com.uoa.di.csr.api.domain.base.ServiceRequestWithActivityAndSsa;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@Document(collection = "service_requests")
public class TreeDebrisRequest extends ServiceRequestWithActivityAndSsa {

    @Field(value = "where_is_located")
    private String whereIsTheDebrisLocated;

}
