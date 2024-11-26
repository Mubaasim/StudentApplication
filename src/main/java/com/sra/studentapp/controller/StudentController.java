package com.sra.studentapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sra.studentapp.model.Student;
import com.sra.studentapp.model.StudentDtoForLogin;
import com.sra.studentapp.model.StudentDtoForUpdate;
import com.sra.studentapp.service.StudentService;

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
public class StudentController {

	@Autowired
	private StudentService studentService;

	@PostMapping("/register")
	public Student registerStudent(@RequestBody Student s) {
		return studentService.registerStudent(s);
	}
	
	@PostMapping("/update")
	public StudentDtoForUpdate updateStudent(@RequestBody StudentDtoForUpdate s) {
		return studentService.updateStudent(s);
	}

	@PostMapping("/login")
	public String loginStudent(@RequestBody StudentDtoForLogin loginData) {
		return studentService.loginStudent(loginData.getUserName(), loginData.getPassword());
	}

	@GetMapping("/userdetails/{userName}")
	public StudentDtoForUpdate getUserDetails(@PathVariable String userName) {
		return studentService.getStudent(userName);
	}
}
