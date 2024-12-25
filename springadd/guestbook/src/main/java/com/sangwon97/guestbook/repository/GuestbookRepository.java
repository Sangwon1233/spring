package com.sangwon97.guestbook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sangwon97.guestbook.domain.entity.GuestbookEntity;

// @Repository
public interface GuestbookRepository extends JpaRepository<GuestbookEntity,Long> {
      //, QuerydslPredicateExecutor<GuestbookEntitiy> {
}
