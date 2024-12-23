package com.sangwon97.todo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sangwon97.todo.domain.TodoEntity;


public interface TodoRepository extends JpaRepository<TodoEntity,Long>  {
  @Modifying
  @Query("update todo t set t.done = true where t.id= :id") //여기서 쓰는거는 엔티티 아이디를 사용 함
  int updateTodoDoneById(@Param("id") Long id);//파리미터 바인딩 시작

  List<TodoEntity> findByOrderByTaskDescIdAsc();
}
