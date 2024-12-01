package com.sra.studentapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sra.studentapp.model.Student;
import com.sra.studentapp.model.StudentDtoForUpdate;
import com.sra.studentapp.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public Student registerStudent(Student s) {
		if(studentRepository.existsByUserName(s.getUserName())) {
			return null;
		}
		s.setPassword(passwordEncoder.encode(s.getPassword()));
		Student student = studentRepository.save(s);
		student.setPassword("");
		return student;
		
	}
	
	@Override
	public StudentDtoForUpdate updateStudent(StudentDtoForUpdate s) {
		Optional<Student> student = studentRepository.findById(s.getId());
		student.get().setName(s.getName());
		student.get().setEmail(s.getEmail());
		student.get().setPhone(s.getPhone());
		studentRepository.save(student.get());
		return s;
	}
	
	@Override
	public String loginStudent(String userName, String password) {
		// TODO Auto-generated method stub
		Optional<Student> student = studentRepository.findByUserName(userName);
		
		if(student.isPresent()) {
			boolean match = passwordEncoder.matches(password, student.get().getPassword());
			if(match) {
				return student.get().getId();
			}
		}
		return null;
	}
	
	@Override
	public StudentDtoForUpdate getStudent(String id) {
		// TODO Auto-generated method stub
		Optional<Student> student =  studentRepository.findById(id);
		StudentDtoForUpdate updatedDetails = new StudentDtoForUpdate();
		updatedDetails.setUserName(student.get().getUserName());
		updatedDetails.setEmail(student.get().getEmail());
		updatedDetails.setName(student.get().getName());
		updatedDetails.setId(student.get().getId());
		updatedDetails.setPhone(student.get().getPhone());
		return updatedDetails;
	}

	@Override
	public String getUserName(String id) {
		// TODO Auto-generated method stub
		return studentRepository.findById(id).get().getUserName();
	}
}
