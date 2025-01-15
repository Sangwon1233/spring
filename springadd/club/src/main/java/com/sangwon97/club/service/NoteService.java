package com.sangwon97.club.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sangwon97.club.entity.Member;
import com.sangwon97.club.entity.Note;
import com.sangwon97.club.entity.dto.NoteDto;

import jakarta.transaction.Transactional;

@Service
@Transactional
public interface NoteService{
  Long register(NoteDto noteDto);

  Optional<NoteDto> get(Long num);

  void modify(NoteDto noteDto);
  
  void remove(Long num);

  List<NoteDto> getAllWithwriter(String writerEmail);

  List<NoteDto> listByEmail(String email);
  List<NoteDto> listByMno(Long mno);
  List<NoteDto> listAll();

  default Note dtoToEntity(NoteDto noteDto){
    Note note = Note.builder()
      .num(noteDto.getNum())
      .title(noteDto.getTitle())
      .content(noteDto.getContent())
      .member(Member.builder().email(noteDto.getWriterEmail()).build())
      .build();

      return note;
  }
  default NoteDto entityToDTO(Note note) {
    NoteDto noteDTO = NoteDto.builder()
            .num(note.getNum())
            .title(note.getTitle())
            .content(note.getContent())
            .writerEmail(note.getMember().getEmail())
            .regDate(note.getRegDate())
            .modDate(note.getModDate())
            .build();

    return noteDTO;
}


}


