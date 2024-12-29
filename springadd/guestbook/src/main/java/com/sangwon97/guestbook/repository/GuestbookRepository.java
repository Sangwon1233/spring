package com.sangwon97.guestbook.repository;

import java.util.Optional;

import org.apache.logging.log4j.message.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.sangwon97.guestbook.domain.entity.Guestbook;

@Repository
public interface GuestbookRepository extends JpaRepository<Guestbook,Long>
      , QuerydslPredicateExecutor<Guestbook>
            
{
  Message save(Optional<Guestbook> findbook);
  
  }