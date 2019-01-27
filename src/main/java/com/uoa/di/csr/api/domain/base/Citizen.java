package com.uoa.di.csr.api.domain.base;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@CompoundIndexes({
        @CompoundIndex(name = "full_name", def = "{'first_name' : 1, 'last_name': 1}", unique = true)
})
@Document(collection = "citizens")
public class Citizen {

    //TODO CUSTOM VALIDATIONS SHOULD BE ADDED

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

    @Field(value = "upvoted_sr_ids")
    private Set<String> upvotedSrIds = new HashSet<>();

    @Field(value = "upvoted_wards")
    private Set<Integer> upvotedWards = new HashSet<>();
}
