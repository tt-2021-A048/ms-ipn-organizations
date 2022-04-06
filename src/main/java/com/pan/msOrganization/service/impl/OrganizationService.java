package com.pan.msOrganization.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pan.msOrganization.model.Organization;
import com.pan.msOrganization.repository.IOrganizationRepository;
import com.pan.msOrganization.service.IOrganizationService;

@Service
public class OrganizationService implements IOrganizationService{
	
	@Autowired
	private IOrganizationRepository organizationRepo;
	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;
	@Override
	public List<Organization> findAll() {
		return organizationRepo.findAll();
	}

	@Override
	public Organization create(Organization organization) {
		organization.setId(sequenceGeneratorService.getSequenceNumber(Organization.SEQUENCE_NAME));
		return organizationRepo.insert(organization);
	}

	@Override
	public Organization update(Organization organization) {
		return organizationRepo.save(organization);
	}

	@Override
	public void delete(Integer id) {
		organizationRepo.deleteById(id);
	}

	@Override
	public Optional<Organization> findById(Integer id) {
		return organizationRepo.findById(id);
	}

	@Override
	public Optional<Organization> findByName(String name) {
		return organizationRepo.findByName(name);
	}
	
	
}
