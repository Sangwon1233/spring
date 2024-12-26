package com.sangwon97.guestbook.repository;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.sangwon97.guestbook.domain.entity.GuestbookEntity;

@SpringBootTest
public interface GuestbookRepositoryTests extends JpaRepository<GuestbookEntity,Long> {
  
}
