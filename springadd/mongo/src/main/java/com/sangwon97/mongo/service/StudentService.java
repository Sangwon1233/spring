package com.sangwon97.mongo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sangwon97.mongo.entity.Student;
import com.sangwon97.mongo.repository.StudentRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StudentService {
  private final StudentRepository repository;

//
public void register(Student student){
  repository.save(student);

}
//
public List<Student> list(){
  return repository.findAll();

}
//
public Optional<Student> get(Long no){
  return repository.findById(no);

}

}

