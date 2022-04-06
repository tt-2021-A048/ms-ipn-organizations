package com.pan.msOrganization.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document("organization")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Organization{
	//CUSTOM ID
	@Transient
	public static final String SEQUENCE_NAME="organization_sequence";
	
	@Id
	private int id;
	
	@Indexed(unique = true)
	@NotBlank(message = "The name is obligatory")
	@Size(min=3, max=255, message="name must contain al least 3 characters")
	private String name;
}
