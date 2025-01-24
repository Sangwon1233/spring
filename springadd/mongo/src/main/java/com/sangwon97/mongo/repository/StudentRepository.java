package com.sangwon97.mongo.repository;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.sangwon97.mongo.entity.Student;

public interface StudentRepository extends MongoRepository<Student, Long> {
  
}
