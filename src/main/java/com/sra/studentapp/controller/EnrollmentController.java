package com.sra.studentapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sra.studentapp.model.Course;
import com.sra.studentapp.model.Enrollment;
import com.sra.studentapp.service.EnrollmentService;

@RestController
public class EnrollmentController {
	
	@Autowired
	EnrollmentService enrollmentService;
	
	@PostMapping("/enroll")
	public String enroll(@RequestBody Enrollment enrollment) {
		return enrollmentService.enroll(enrollment);
	}
	
	@PostMapping("/drop")
	public String drop(@RequestBody String id) {
		return enrollmentService.drop(id);
	}
	
	@GetMapping("/enrolled/{studentId}")
	public List<Course> getCourseList(@PathVariable String studentId) {
		return enrollmentService.getCourseList(studentId);
	}
}
