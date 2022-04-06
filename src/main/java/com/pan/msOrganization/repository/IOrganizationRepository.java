package com.pan.msOrganization.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pan.msOrganization.model.Organization;

public interface IOrganizationRepository extends MongoRepository<Organization, Integer>{
	Optional<Organization> findByName(String name);
}