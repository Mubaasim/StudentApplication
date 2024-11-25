package com.sra.studentapp.service;

import java.util.List;
import java.util.Optional;

import com.sra.studentapp.model.Student;


public interface StudentService {
	public List<Student> getStudents();
	public Optional<Student> getStudent(String userName);

	public Student registerStudent(Student s);
	public Optional<Student> loginStudent(String userName, String password);
}
