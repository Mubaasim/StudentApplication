package com.sra.studentapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<String> registerStudent(Student s) {
		try {
			if (studentRepository.existsByUserName(s.getUserName())) {
				return ResponseEntity.status(409).body("Username already exists"); // conflict
			}
			s.setPassword(passwordEncoder.encode(s.getPassword()));
			Student student = studentRepository.save(s);
			return ResponseEntity.status(201).body("Student successfully registered :" + student.getId()); // created
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Error Registering : " + e.getMessage());

			return ResponseEntity.status(500).body("An error occurred while registering a new Student"); // internal server error
		}

	}

	@Override
	public ResponseEntity<String> updateStudent(StudentDtoForUpdate s) {
		try {
			Optional<Student> studentOptional = studentRepository.findById(s.getId());

			if (studentOptional.isPresent()) {
				Student student = studentOptional.get();
				student.setName(s.getName());
				student.setEmail(s.getEmail());
				student.setPhone(s.getPhone());
				studentRepository.save(student);
				return ResponseEntity.ok("Update Successful"); // 200
			} else {
				return ResponseEntity.status(404).body("Student not found with ID: " + s.getId()); // not found
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Error while updating : " + e.getMessage());

			return ResponseEntity.status(500).body("An error occurred while updating Student details"); // internal server error
		}
		
	}

	@Override
	public ResponseEntity<String> loginStudent(String userName, String password) {
		// TODO Auto-generated method stub
		try {
			Optional<Student> student = studentRepository.findByUserName(userName);

			if (student.isPresent()) {
				boolean match = passwordEncoder.matches(password, student.get().getPassword());
				if (match) {
					return ResponseEntity.ok(String.valueOf(student.get().getId()));
				} else {
					return ResponseEntity.status(401).body("Invalid credentials"); // Unauthorized
				}
			}
			return ResponseEntity.status(404).body("User not found"); // not found
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Error while logging in : " + e.getMessage());

			return ResponseEntity.status(500).body("An error occurred during login"); // internal server error
		}
	}

	@Override
	public ResponseEntity<StudentDtoForUpdate> getStudent(String id) {
		// TODO Auto-generated method stub
		try {
			Optional<Student> studentOptional = studentRepository.findById(id);
			if (studentOptional.isPresent()) {
				Student student = studentOptional.get();

				StudentDtoForUpdate updatedDetails = new StudentDtoForUpdate();
				updatedDetails.setUserName(student.getUserName());
				updatedDetails.setEmail(student.getEmail());
				updatedDetails.setName(student.getName());
				updatedDetails.setId(student.getId());
				updatedDetails.setPhone(student.getPhone());
				return ResponseEntity.ok(updatedDetails);
			} else {
				return ResponseEntity.status(404).body(null);
			}
		} catch (Exception e) {
			System.err.println("Error retrieving student: " + e.getMessage());

			return ResponseEntity.status(500).body(null); // internal server error
		}
	}

	@Override
	public ResponseEntity<String> getUserName(String id) {
		// TODO Auto-generated method stub
		try {
			Optional<Student> studentOptional = studentRepository.findById(id);
			if (studentOptional.isPresent()) {
				return ResponseEntity.ok(studentOptional.get().getUserName());
			} else {
				return ResponseEntity.status(404).body("Student not found");
			}

		} catch (Exception e) {
			System.err.println("Error fetching username: " + e.getMessage());
			;

			return ResponseEntity.status(500).body("An error occurred while fetching the username"); // internal server error
		}
	}
}
