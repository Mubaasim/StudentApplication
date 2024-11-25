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
	String id;
	
	@Indexed(unique = true)
	String userName;
	
	String password;
	String name;
	String phone;
	String email;
	
}
