package com.sra.studentapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sra.studentapp.model.Course;
import com.sra.studentapp.service.CourseService;

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	
	@PostMapping("/addcourse")
	public ResponseEntity<String> addCourse(@RequestBody Course course ) {
		return courseService.addCourse(course);
	}
	
	@PostMapping("/deletecourse/{id}")
	public ResponseEntity<String> deleteCourse(@PathVariable String id ) {
		return courseService.deleteCourse(id);
	}
	
	@GetMapping("/courses")
	public ResponseEntity<List<Course>> getAllCourses(){
		return courseService.getAllCourses();
	}
	
}
