package com.sra.studentapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(collection = "studentdb")
@AllArgsConstructor
@NoArgsConstructor
public class Student {
	@Id
	private String id;
	
	@Indexed(unique = true)
	private String email;
	
	private String password;
	
	private String firstName;
	private String lastName;
	private String dateOfBirth;
	private String address;
	private String phoneNumber;
}
