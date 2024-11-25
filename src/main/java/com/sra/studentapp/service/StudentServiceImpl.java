package com.sra.studentapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sra.studentapp.model.Student;
import com.sra.studentapp.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public Student registerStudent(Student s) {
		s.setPassword(passwordEncoder.encode(s.getPassword()));
		// TODO Auto-generated method stub
		if (studentRepository.existsByUserName(s.getUserName())) {
	        System.out.println("UserName already exists");
	        return null;  // or throw a custom exception
	    }
		
		return studentRepository.save(s);
		
	}
	
	@Override
	public Optional<Student> loginStudent(String userName, String password) {
		// TODO Auto-generated method stub
		Optional<Student> student = studentRepository.findByUserName(userName);
		
		if(student.isPresent()) {
			boolean match = passwordEncoder.matches(password, student.get().getPassword());
			if(!match) {
				return Optional.empty();
			}
		}
		return student;
	}
	
	
	
	
	@Override
	public List<Student> getStudents() {
		// TODO Auto-generated method stub
		return studentRepository.findAll();
	}

	

	@Override
	public Optional<Student> getStudent(String userName) {
		// TODO Auto-generated method stub
		return studentRepository.findById(userName);
	}
}
