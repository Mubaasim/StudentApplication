package com.sra.studentapp.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.sra.studentapp.model.Course;
import com.sra.studentapp.model.Enrollment;

public interface EnrollmentService {
	public ResponseEntity<String> enroll(Enrollment enrollment);
	public ResponseEntity<String> drop(Enrollment enrollment);
	public ResponseEntity<List<Course>> getCourseList(String studentId);
}
