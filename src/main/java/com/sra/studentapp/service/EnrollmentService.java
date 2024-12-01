package com.sra.studentapp.service;

import java.util.List;

import com.sra.studentapp.model.Course;
import com.sra.studentapp.model.Enrollment;

public interface EnrollmentService {
	public String enroll(Enrollment enrollment);
	public String drop(Enrollment enrollment);
	public List<Course> getCourseList(String studentId);
}
