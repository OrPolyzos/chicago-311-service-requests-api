package com.uoa.di.csr.api.repository;

import com.uoa.di.csr.api.domain.base.Citizen;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "citizens", path = "citizens")
public interface CitizenRepository extends MongoRepository<Citizen, String>, CitizenCustomRepository {

    List<Citizen> findAllByFirstNameAndLastName(String firstName, String lastName);
}
