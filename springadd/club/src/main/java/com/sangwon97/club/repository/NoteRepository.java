package com.sangwon97.club.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sangwon97.club.entity.Note;

public interface NoteRepository extends JpaRepository<Note ,Long> {
  Note findByNum(Long num);

  List<Note> findByMemberMno(Long mno);

  List<Note> findByMemberEmail(String email);

  List<Note> getAllWithWriter(String num);


}
