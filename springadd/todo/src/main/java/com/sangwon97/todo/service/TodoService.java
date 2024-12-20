package com.sangwon97.todo.service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.sangwon97.todo.domain.TodoEntity;
import com.sangwon97.todo.dto.TodoListDto;
import com.sangwon97.todo.dto.TodoWirteDto;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TodoService {
  private final JpaRepository<TodoEntity, Long> repository;
  // 목록 조회
  public List<TodoListDto> list(){
    // return repository.
    return repository.findAll().stream().map(TodoListDto::new).toList();
  }
  // 등록
  public void wirte(TodoWirteDto dto){
    repository.save(dto.todoEntity());
  }

  // 삭제



}
