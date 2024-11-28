package com.sra.studentapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
	public String addCourse(@RequestBody Course course ) {
		return courseService.addCourse(course);
	}
	
	@PostMapping("/deletecourse/{id}")
	public String deleteCourse(@PathVariable String id ) {
		return courseService.deleteCourse(id);
	}
	
}
