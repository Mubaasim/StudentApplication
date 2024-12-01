package com.sra.studentapp.service;

import com.sra.studentapp.model.Student;
import com.sra.studentapp.model.StudentDtoForUpdate;


public interface StudentService {
	public StudentDtoForUpdate getStudent(String id);

	public Student registerStudent(Student s);
	public StudentDtoForUpdate updateStudent(StudentDtoForUpdate s);
	public String loginStudent(String userName, String password);
	
	public String getUserName(String id);
}
