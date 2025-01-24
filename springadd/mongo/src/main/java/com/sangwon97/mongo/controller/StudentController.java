package com.sangwon97.mongo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sangwon97.mongo.entity.Student;
import com.sangwon97.mongo.service.StudentService;

import lombok.AllArgsConstructor;


@RestController
@AllArgsConstructor

public class StudentController {
  private StudentService service;
  
  @PostMapping("resister")
  public void register(@RequestBody Student student){

  }

  @GetMapping("student")
  public List<Student> list(){
    return service.list();
  }

  @GetMapping("student/{no}")
  public Student get(@PathVariable Long no){
    return service.get(no).orElse(null);
  }

}
