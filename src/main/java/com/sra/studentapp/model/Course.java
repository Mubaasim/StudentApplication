package com.sra.studentapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(collection = "coursedb")
@AllArgsConstructor
@NoArgsConstructor
public class Course {
	@Id
	private String id;
	
	private String courseId;
	private String title;
	private String description;
}
