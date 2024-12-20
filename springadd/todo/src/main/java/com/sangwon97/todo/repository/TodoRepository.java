package com.sangwon97.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sangwon97.todo.domain.TodoEntity;


public interface TodoRepository extends JpaRepository<TodoEntity,Long>  {
}
