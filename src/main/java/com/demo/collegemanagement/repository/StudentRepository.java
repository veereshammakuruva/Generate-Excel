package com.demo.collegemanagement.repository;

import com.demo.collegemanagement.model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository  extends MongoRepository<Student, Integer> {
}
