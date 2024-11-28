package com.sra.studentapp.model;

import lombok.Data;

@Data
public class StudentDtoForUpdate {
	private String id;
	private String name;
	private String phone;
	private String email;
}
