package com.sra.studentapp.model;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class StudentDtoForUpdate {
	@Id
	String id;
	String name;
	String phone;
	String email;
}
