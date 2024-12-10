package com.sra.studentapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sra.studentapp.model.Student;

@Repository
public interface StudentRepository extends MongoRepository<Student, String>{
	
	Student findByEmail(String email);
	boolean existsByEmail(String email);
}
