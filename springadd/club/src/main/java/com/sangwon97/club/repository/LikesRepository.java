package com.sangwon97.club.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sangwon97.club.entity.Likes;
import com.sangwon97.club.entity.composite.*;

public interface LikesRepository extends JpaRepository<Likes,LikesId> {
  
}
