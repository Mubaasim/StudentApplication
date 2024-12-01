package com.sra.studentapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sra.studentapp.model.Course;
import com.sra.studentapp.repository.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService{

	@Autowired
	CourseRepository courseRepository;
	
	@Override
	public String addCourse(Course c) {
		// TODO Auto-generated method stub
		courseRepository.save(c);
		return "Success";
	}

	@Override
	public String deleteCourse(String id) {
		// TODO Auto-generated method stub
		courseRepository.deleteById(id);
		return "Success";
	}

	@Override
	public List<Course> getAllCourses() {
		// TODO Auto-generated method stub
		return courseRepository.findAll();
	}
	
	

}
