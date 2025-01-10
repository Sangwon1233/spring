package com.sangwon97.club.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sangwon97.club.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{
  Member findByEmail(String email);
  Member findByEmailAndFromSocial(String email, boolean fromSocial);
}