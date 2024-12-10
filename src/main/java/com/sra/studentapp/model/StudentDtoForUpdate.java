package com.sra.studentapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDtoForUpdate {
	private String id;
	
	private String email;
	private String firstName;
	private String lastName;
	private String dateOfBirth;
	private String address;
	private String phoneNumber;
}
