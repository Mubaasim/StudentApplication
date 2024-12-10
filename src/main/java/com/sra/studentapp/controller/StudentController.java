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
import com.sra.studentapp.model.StudentDtoForUpdate;
import com.sra.studentapp.model.User;
import com.sra.studentapp.service.StudentService;
import com.sra.studentapp.service.UserService;

import jakarta.servlet.http.HttpServletResponse;

@CrossOrigin(origins =  "http://localhost:3000", allowCredentials = "true")
@RestController
public class StudentController {

	@Autowired
	private StudentService studentService;

	@Autowired
    private UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<String> registerStudent(@RequestBody Student s) {
		return studentService.registerStudent(s);
	}
	
	@PostMapping("/update")
	public ResponseEntity<String> updateStudent(@RequestBody StudentDtoForUpdate s) {
		return studentService.updateStudent(s);
	}

	@PostMapping("/login")
	public ResponseEntity<String> loginStudent(@RequestBody User loginData, HttpServletResponse response) {
		return userService.verify(loginData, response);
	}

	@GetMapping("/userdetails/{id}")
	public ResponseEntity<StudentDtoForUpdate> getUserDetails(@PathVariable String id) {
		System.out.println(studentService.getStudent(id));
		return studentService.getStudent(id);
	}
	
	@GetMapping("/getusername/{id}")
	public ResponseEntity<String> getFirstName(@PathVariable String id) {
		return studentService.getFirstName(id);
	}
}
