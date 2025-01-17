package com.sangwon97.club.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.sangwon97.club.entity.Note;

public interface NoteRepository extends JpaRepository<Note ,Long> {
  Note findByNum(Long num);

  List<Note> findByMemberMno(Long mno);

  List<Note> findByMemberEmail(String email);
  
  @Query("select n, count(distinct l) as likescnt,count(distinct a) as attachcnt \r\n" +
   "from tbl_notes n left join tbl_likes l on l.note.num = n.num left join tnl_attach a on a.note.num = n.num \r\n" +
   "where n.member.email = :email group by n")
  List<Object> findNotesBy(@Param("email") String email);

  @Query("select n, count(distinct l) as likescnt,count(distinct a) as attachcnt \r\n" +
   "from tbl_notes n left join tbl_likes l on l.note.num = n.num left join tnl_attach a on a.note.num = n.num \r\n" +
   "group by n")
  List<Object> findNotes();



  

}
