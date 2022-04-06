package com.pan.msOrganization.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pan.msOrganization.model.Organization;
import com.pan.msOrganization.service.impl.OrganizationService;

@RestController
@RequestMapping("/api/organizations")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OrganizationController {
	
	@Autowired
	private OrganizationService organizationService;
	
	@GetMapping
	public ResponseEntity<List<Organization>> findAll(){
		return ResponseEntity.ok(organizationService.findAll());
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Organization> findById(@PathVariable("id") Integer id){
		return organizationService.findById(id)
				.map(ResponseEntity::ok)
				.orElseGet( () -> ResponseEntity.notFound().build() );
	}
	
	@PostMapping
	public ResponseEntity<Organization> createUser(@Valid @RequestBody Organization organization){
		return new ResponseEntity<Organization>( organizationService.create(organization) , HttpStatus.CREATED );
	}
	
	@PutMapping	
	public ResponseEntity<Organization> update(@Valid @RequestBody Organization organization){
		return organizationService.findById(organization.getId())
				.map( c -> ResponseEntity.ok(organizationService.update(organization)) )
				.orElseGet( () -> ResponseEntity.notFound().build() );
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Organization> delete(@PathVariable("id") Integer id){
		return organizationService.findById(id)
				.map( c -> {
					organizationService.delete(id);
					return ResponseEntity.ok(c);
					})
				.orElseGet( () -> ResponseEntity.notFound().build() );
	}
	
}
