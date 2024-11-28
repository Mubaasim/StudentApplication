package com.sra.studentapp.service;

import com.sra.studentapp.model.Course;

public interface CourseService {
	public String addCourse(Course course);
	public String deleteCourse(String id);
}
