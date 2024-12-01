package com.sra.studentapp.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sra.studentapp.model.Course;
import com.sra.studentapp.model.Enrollment;
import com.sra.studentapp.repository.CourseRepository;
import com.sra.studentapp.repository.EnrollmentRepository;

@Service
public class EnrollmentServiceImpl implements EnrollmentService{

	@Autowired
	EnrollmentRepository enrollmentRepository;
	
	@Autowired
	CourseRepository courseRepository;

	@Override
	public String enroll(Enrollment e) {
		// TODO Auto-generated method stub
		if(enrollmentRepository.existsByCourseIdAndStudentId(e.getCourseId(),e.getStudentId())) {
			return null;
		}
		Enrollment enrollment =  enrollmentRepository.save(e);
		
		return enrollment.getId();
	}

	@Override
	public String drop(Enrollment e) {
		// TODO Auto-generated method stub
		enrollmentRepository.deleteByCourseIdAndStudentId(e.getCourseId(), e.getStudentId());
		return "Success";
	}

	@Override
	public List<Course> getCourseList(String studentId) {
		// TODO Auto-generated method stub
		List<Enrollment> enrolledList = enrollmentRepository.findByStudentId(studentId);
		List<String> courseIdList = enrolledList.stream()
				.map(Enrollment::getCourseId)
				.collect(Collectors.toList());
		return courseRepository.findAllById(courseIdList);
	}
	
	
}
