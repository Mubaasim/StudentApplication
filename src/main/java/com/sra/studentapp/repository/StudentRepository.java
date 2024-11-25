package com.sra.studentapp.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.sra.studentapp.model.Student;

@Repository
public interface StudentRepository extends MongoRepository<Student, String>{
	
	Optional<Student> findByUserName(String username);
	boolean existsByUserName(String username);
}
