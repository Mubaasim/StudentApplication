package com.sra.studentapp.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sra.studentapp.model.Course;
import com.sra.studentapp.model.Enrollment;
import com.sra.studentapp.repository.CourseRepository;
import com.sra.studentapp.repository.EnrollmentRepository;

@Service
public class EnrollmentServiceImpl implements EnrollmentService{

	@Autowired
	private EnrollmentRepository enrollmentRepository;
	
	@Autowired
	private CourseRepository courseRepository;
	
	@Autowired
	private EmailService emailService;
	

	@Override
	public ResponseEntity<String> enroll(Enrollment e) {
		// TODO Auto-generated method stub
		try {
			if(enrollmentRepository.existsByCourseIdAndStudentId(e.getCourseId(),e.getStudentId())) {
				return ResponseEntity.status(409).body("Student is already enrolled in this course");
			} else {
				emailService.sendEnrollmentNotification(
						"mubaasim@gmail.com",
						"Java",
						"Mubaasim"
                );
				enrollmentRepository.save(e);
				return ResponseEntity.ok("Successfully Enrolled in the course");
			}
		} catch (Exception ex) {
			// TODO: handle exception
			System.err.println("Error during enrollment: " + ex.getMessage()); 
			ex.printStackTrace();
			
			return ResponseEntity.status(500).body("An error occurred during enrollment"); //internal error
		}
	}

	@Override
	public ResponseEntity<String> drop(Enrollment e) {
		// TODO Auto-generated method stub
		try {
			if (!enrollmentRepository.existsByCourseIdAndStudentId(e.getCourseId(), e.getStudentId())) {
	            return ResponseEntity.status(404).body("Enrollment not found");
	        }

	        enrollmentRepository.deleteByCourseIdAndStudentId(e.getCourseId(), e.getStudentId());
	        return ResponseEntity.ok("Dropped successfully");
		} catch (Exception ex) {
			// TODO: handle exception
			System.err.println("Error during course drop: " + ex.getMessage());

	        return ResponseEntity.status(500).body("An error occurred while dropping the course");
		}
		
	}

	@Override
	public ResponseEntity<List<Course>> getCourseList(String studentId) {
		// TODO Auto-generated method stub
		try {
			List<Enrollment> enrolledList = enrollmentRepository.findByStudentId(studentId);

	        if (enrolledList.isEmpty()) {
	            return ResponseEntity.ok(Collections.emptyList());
	        }

	        List<String> courseIdList = enrolledList.stream()
	                                                .map(Enrollment::getCourseId)
	                                                .collect(Collectors.toList());

	        List<Course> courseList = courseRepository.findAllById(courseIdList);
	        return ResponseEntity.ok(courseList);
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Error fetching course list: " + e.getMessage());

	        return ResponseEntity.status(500).body(null);
		}
	}
	
	
}
