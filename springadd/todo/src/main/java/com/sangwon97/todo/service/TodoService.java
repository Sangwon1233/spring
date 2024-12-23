package com.sangwon97.todo.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.sangwon97.todo.domain.TodoEntity;
import com.sangwon97.todo.dto.TodoListDto;
import com.sangwon97.todo.dto.TodoWirteDto;
import com.sangwon97.todo.repository.TodoRepository;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TodoService {
  private  TodoRepository repository;
  private EntityManager manager;

  @PostConstruct
  public void init(){
    repository.saveAll(
      Stream.of(
        TodoEntity.builder().task("1").build(),
        TodoEntity.builder().task("2").build(),
        TodoEntity.builder().task("3").build()
        ).toList()
    );
  }
  

  // 목록 조회
  public List<TodoListDto> list(){
    // return repository.
    // return repository.findAll(Sort.by(Direction.DESC,"id")).stream().map(TodoListDto::new).toList();
    // return repository.findByOrderByTaskDesc().stream().map(TodoListDto::new).toList(); //이거 안됐음
    return repository.findAll(Sort.by(Order.desc("task"),Order.asc("id"))).stream().map(TodoListDto::new).toList();
  }
  // 등록
  public void wirte(TodoWirteDto dto){
    repository.save(dto.todoEntity());
  }

  // 삭제
  public void remove(Long id) {
    // repository.delete(TodoEntity.builder().id(id).build());
    repository.deleteById(id); 
  }

  //수정
  @Transactional
  public void modify(Long id) {
  //  Optional<TodoEntity> entity= repository.findById(id);
  //  entity.ifPresent(e-> {e.setDone(true); repository.save(e);}); //엔티티 후 자동 저장이 세이브
  // repository.save(TodoEntity.builder().id(id).done(true).task("직접 값 넣기").build());
  repository.updateTodoDoneById (id);
  
  }
  //Using EntityManger
  @Transactional
  public void modify2(Long id){
    // Optional.of(manager.find(TodoEntity.class, id).ifPresent(e->e.setDone(true));
    manager.find(TodoEntity.class, id).setDone(true);
  }

}
