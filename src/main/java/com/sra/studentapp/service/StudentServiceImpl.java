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
	public Student registerStudent(Student s, boolean register) {
		if(register) {
			s.setPassword(passwordEncoder.encode(s.getPassword()));
			if (studentRepository.existsByUserName(s.getUserName()) && register) {
		        System.out.println("UserName already exists");
		        return null;
		    }
		}
		else {
			Student studentFromDb = studentRepository.findById(s.getId()).orElse(null);
			s.setPassword(studentFromDb.getPassword());
		}
		// TODO Auto-generated method stub
		
		
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
	public Optional<Student> getStudent(String userName) {
		// TODO Auto-generated method stub
		return studentRepository.findByUserName(userName);
	}
	
	
	
	@Override
	public List<Student> getStudents() {
		// TODO Auto-generated method stub
		return studentRepository.findAll();
	}

	

	

}
