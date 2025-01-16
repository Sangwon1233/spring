package com.sangwon97.club.entity;

import java.lang.reflect.Member;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.sangwon97.club.entity.composite.LikesId;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity(name = "tbl_likes")
@EnableJpaRepositories
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(LikesId.class)
public class Likes extends BaseEntity{
  @Id
  @ManyToOne(fetch = FetchType.LAZY)
  private Member member;
  @Id
  @ManyToOne(fetch = FetchType.LAZY)
  private Note note;

}
