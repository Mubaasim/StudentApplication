package com.sra.studentapp.service;

import org.springframework.http.ResponseEntity;

import com.sra.studentapp.model.Student;
import com.sra.studentapp.model.StudentDtoForUpdate;


public interface StudentService {
	public ResponseEntity<String> registerStudent(Student s);
	public ResponseEntity<String> updateStudent(StudentDtoForUpdate s);
	
	//public ResponseEntity<String> loginStudent(String userName, String password);
	
	public ResponseEntity<StudentDtoForUpdate> getStudent(String id);
	public ResponseEntity<String> getFirstName(String id);
}
