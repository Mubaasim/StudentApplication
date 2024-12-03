package com.sra.studentapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sra.studentapp.model.Course;

@Repository
public interface CourseRepository extends MongoRepository<Course, String>{
	public boolean existsByCourseId(String courseId);
	public void deleteByCourseId(String courseId);
}
