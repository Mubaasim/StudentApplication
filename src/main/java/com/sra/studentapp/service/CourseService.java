package com.sra.studentapp.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.sra.studentapp.model.Course;

public interface CourseService {
	public ResponseEntity<String> addCourse(Course course);
	public ResponseEntity<String> deleteCourse(String id);
	public ResponseEntity<List<Course>> getAllCourses();
}
