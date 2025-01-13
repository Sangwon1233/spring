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
    public NoteDto get(Long num) {
      Note note = noteRepository.findByNum(num);
      // if (result.isPresent()) {
      //     return entityToDTO(result.get());
      // }
        
      return null;
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
    public void remove(Long num) {
        if (noteRepository.findById(num).isPresent()) {
            noteRepository.deleteById(num);    
        }
    }

    
    @Override
    public List<NoteDto> getAllWithwriter(String writerEmail) {
      List<Note> noteList = noteRepository.findByMemberEmail(writerEmail);
      return noteList.stream().map(note -> entityToDTO(note)).collect(Collectors.toList());
    }

}
  

