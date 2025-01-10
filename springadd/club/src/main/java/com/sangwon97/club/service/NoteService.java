package com.sangwon97.club.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sangwon97.club.entity.Member;
import com.sangwon97.club.entity.Note;
import com.sangwon97.club.entity.dto.NoteDto;

import jakarta.transaction.Transactional;

@Service
@Transactional
public interface NoteService{
  Long register(NoteDto noteDto);

  NoteDto get(Long num);

  void modify(NoteDto noteDto);
  
  void remove(Long num);

  List<NoteDto> getAllWithwriter(String writerEmail);

  default Note dtoToEntity(NoteDto noteDto){
    Note note = Note.builder()
      .num(noteDto.getNum())
      .title(noteDto.getTitle())
      .content(noteDto.getContent())
      .writer(Member.builder().email(noteDto.getWriterEmail()).build())
      .build();

      return note;
  }
  default NoteDto entityToDTO(Note note) {
    NoteDto noteDTO = NoteDto.builder()
            .num(note.getNum())
            .title(note.getTitle())
            .content(note.getContent())
            .writerEmail(note.getWriter().getEmail())
            .regDate(note.getRegDate())
            .modDate(note.getModDate())
            .build();

    return noteDTO;
}

}
