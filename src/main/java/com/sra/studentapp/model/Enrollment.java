package com.sra.studentapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Document(collection = "enrollmentdb")
@AllArgsConstructor
@NoArgsConstructor
public class Enrollment {
	@Id
	private String id;
	
	private String studentId;
	private String courseId;
}
