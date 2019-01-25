package com.uoa.di.csr.api.domain.base;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Document(collection = "citizens")
public class Citizen {

    @Id
    @Field(value = "citizen_id")
    private String citizenId;

    @Field(value = "first_name")
    private String firstName;

    @Field(value = "last_name")
    private String lastName;

    @Field(value = "telephone_number")
    private String telephoneNumber;

    @Field(value = "address")
    private String address;

    @DBRef(lazy = true)
    @Field(value = "service_request_ids")
    private List<ServiceRequest> serviceRequestsIds = new ArrayList<>();
}
