package com.sra.studentapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sra.studentapp.model.Course;
import com.sra.studentapp.repository.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService{

	@Autowired
	CourseRepository courseRepository;
	
	@Override
	public ResponseEntity<String> addCourse(Course c) {
		// TODO Auto-generated method stub
		try {
			if(courseRepository.existsByCourseId(c.getCourseId())) {
				return ResponseEntity.status(409).body("Course with this ID already exists"); //conflict
			} else {
				courseRepository.save(c);
				return ResponseEntity.status(201).body("Course added successfully"); //created
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Error adding course: " + e.getMessage());
			
			return ResponseEntity.status(500).body("An error occurred while adding the course"); //internal server error
		}
	}

	@Override
	public ResponseEntity<String> deleteCourse(String id) {
		// TODO Auto-generated method stub
		try {
			if(!courseRepository.existsByCourseId(id)) {
				return ResponseEntity.status(404).body("Course with this ID does not exist"); //not found
			}
			courseRepository.deleteByCourseId(id);
			return ResponseEntity.ok("Course deleted successfully");
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Error deleting course: " + e.getMessage());
			
			return ResponseEntity.status(500).body("An error occurred while deleting the course"); //internal server error
		}
	}

	@Override
	public ResponseEntity<List<Course>> getAllCourses() {
		// TODO Auto-generated method stub
		try {
			return ResponseEntity.ok(courseRepository.findAll());
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Error deleting course: " + e.getMessage());
			
			return ResponseEntity.status(500).body(null);
		}
	}
}
