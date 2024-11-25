package com.sra.studentapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sra.studentapp.model.Student;
import com.sra.studentapp.service.StudentService;

import jakarta.servlet.http.HttpSession;


@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	@PostMapping("/register")//both update and register
	public String registerStudent(@RequestBody Student s) {
		Student student = studentService.registerStudent(s);
		return student == null ? "Username already exists" : "Success";
	}
	
	@PostMapping("/login")
    public ResponseEntity<?> loginStudent(@RequestBody Student loginData, HttpSession session) {
        Optional<Student> student = studentService.loginStudent(loginData.getUserName(), loginData.getPassword());
        if (student.isPresent()) {
        	System.out.println("Login Successful");
            session.setAttribute("current", student.get());
            System.out.println(session.getAttribute("current").toString());
            return ResponseEntity.ok(student);
        } else {
        	System.out.println("Login failed");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid username or password");
        }
    }
	
	@GetMapping("/userdetails")
	public ResponseEntity<?> getUserDetails(HttpSession session) {
	    Student student = (Student) session.getAttribute("current");
	    if (student == null) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
	                .body("login to view details");
	    }
	    return ResponseEntity.ok(student);
	}
	
	@PostMapping("/logout")
	public ResponseEntity<?> logout(HttpSession session) {
	    session.invalidate();
	    return ResponseEntity.ok("Logged out successfully");
	}
	

	@GetMapping("/students")//just testing
	public List<Student> getStudents() {
		return studentService.getStudents();
	}
	
	@GetMapping("/student/{userName}")//maybe to obtain others
	public Optional<Student> getStudent(@PathVariable String userName) {
		return studentService.getStudent(userName);
	}
}
