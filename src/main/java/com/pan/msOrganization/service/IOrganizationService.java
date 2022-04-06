package com.pan.msOrganization.service;

import java.util.List;
import java.util.Optional;

import com.pan.msOrganization.model.Organization;

public interface IOrganizationService {
	List<Organization> findAll();
	Organization create(Organization organization);
	Organization update(Organization organization);
	void delete(Integer id);
	Optional<Organization> findById(Integer id);
	Optional<Organization> findByName(String name);
}
