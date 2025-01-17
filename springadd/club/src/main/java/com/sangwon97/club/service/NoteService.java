package com.sangwon97.club.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sangwon97.club.entity.Member;
import com.sangwon97.club.entity.Note;
import com.sangwon97.club.entity.dto.Attach;
import com.sangwon97.club.entity.dto.NoteDto;

import jakarta.transaction.Transactional;

@Service
@Transactional
public interface NoteService{
  Long register(NoteDto noteDto);

  Optional<NoteDto> get(Long num);

  void modify(NoteDto noteDto);
  
  int remove(Long num);

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

      noteDto.setAttachDTOs(noteDto.getAttachDTOs().stream().map(a -> Attach.builder()
      .uuid(a.getUuid())
      .origin(a.getOrigin())
      .ext(a.getExt())
      .fileName(a.getFileName())
      .image(a.isImage())
      .mime(a.getMime())
      .path(a.getPath())
      .size(a.getSize())
      .url(a.getUrl())
      .build()).toList()
    );
    return note;
  }
  default NoteDto entityToDTO(Note note) {
    return NoteDto.builder()
    .num(note.getNum())
    .title(note.getTitle())
    .content(note.getContent())
    .writerEmail(note.getMember().getEmail())
    .mno(note.getMember().getMno())
    .regDate(note.getRegDate())
    .modDate(note.getModDate())
    .attachDTOs(note.getAttachs().stream().map(a -> Attach.builder()
      .uuid(a.getUuid())
      .ext(a.getExt())
      .origin(a.getOrigin())  
      .fileName(a.getFileName())
      .image(a.isImage())
      .mime(a.getMime())
      .path(a.getPath())
      .size(a.getSize())
      .url(a.getUrl())
      .build()).toList()
     ).build();
}




}


