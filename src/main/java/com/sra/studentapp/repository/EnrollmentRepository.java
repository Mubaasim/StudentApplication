package com.sra.studentapp.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sra.studentapp.model.Enrollment;

@Repository
public interface EnrollmentRepository extends MongoRepository<Enrollment, String>{
	public boolean existsByCourseIdAndStudentId(String courseId, String studentId);
	public List<Enrollment> findByStudentId(String studentId);
}
