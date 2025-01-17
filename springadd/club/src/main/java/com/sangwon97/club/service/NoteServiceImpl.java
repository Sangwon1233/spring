package com.sangwon97.club.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sangwon97.club.entity.Note;
import com.sangwon97.club.entity.dto.NoteDto;
import com.sangwon97.club.repository.NoteRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {
  
  private final NoteRepository noteRepository;

  @Override
    public Long register(NoteDto noteDTO) {
        Note note = dtoToEntity(noteDTO);

        log.info("===========================");
        log.info(note);
        
        noteRepository.save(note);
        
        return note.getNum();
    }

    @Override
    public Optional<NoteDto> get(Long num) {
      // Note note = noteRepository.findByNum(num);
      // if (result.isPresent()) {
      //     return entityToDTO(result.get());
      // }
        
      return noteRepository.findById(num).map(this::entityToDTO);
    }

    @Override
    public void modify(NoteDto noteDTO) {
        Long num = noteDTO.getNum();
        Optional<Note> result = noteRepository.findById(num);

        if (result.isPresent()) {
            Note note = result.get();
            
            note.changeTitle(noteDTO.getTitle());
            note.changeContent(noteDTO.getContent());

            noteRepository.save(note);
        }
    }

    @Override
    public int remove(Long num) {
        noteRepository.deleteById(num);    
        return 1;

    }

    
    @Override
    public List<NoteDto> getAllWithwriter(String writerEmail) {
      List<Note> noteList = noteRepository.findByMemberEmail(writerEmail);
      return noteList.stream().map(note -> entityToDTO(note)).collect(Collectors.toList());
    }

    // @Override
    // public List<NoteDto> listAll() {
    //   return noteRepository.findAll().stream().map(this::entityToDTO).toList();
    // }

    @Override
    public List<NoteDto> listByEmail(String email) {
      noteRepository.findNotesBy(email).stream().map(o -> {
        NoteDto dto = entityToDTO((Note)o[0]);
        dto.setLikeCnt((Long)o[1]);
        dto.setAttachCnt((Long)o[2]);
        return dto; 
      }).toList();
    }

    @Override
    public List<NoteDto> listByMno(Long mno) {
      return noteRepository.findByMemberMno(mno).stream().map(note -> entityToDTO(note)).collect(Collectors.toList());
    }
    
    @Override
    public List<NoteDto> listAll() {
     return noteRepository.findNotes(writerEmail).stream().map(o -> {
        NoteDto dto = entityToDTO((Note)o[0]);
        dto.setLikeCnt((Long)o[1]);
        dto.setAttachCnt((Long)o[2]);
        return dto; 
      }).toList();
    }

}
  

