package com.sangwon97.guestbook.service;

import java.util.List;

import javax.swing.text.html.parser.Entity;

import com.sangwon97.guestbook.domain.dto.GuestbookDto;
import com.sangwon97.guestbook.domain.dto.GuestbookListDTO;
import com.sangwon97.guestbook.domain.dto.GuestbookModifyDTO;
import com.sangwon97.guestbook.domain.dto.GuestbookViewDTO;
import com.sangwon97.guestbook.domain.dto.GuestbookWriteDTO;
import com.sangwon97.guestbook.domain.entity.Guestbook;

public interface GuestbookService {
    Long writer(GuestbookDto dto);
    void modify(GuestbookModifyDTO dto);
    void remove(Long gno);

    List<GuestbookListDTO> list();
    GuestbookViewDTO get(Long gno);
    //interface를 구체화시키기 static,default
    default Guestbook toEntity(GuestbookDto dto){
        return Guestbook.builder()
        .gno(dto.getGno())
        .title(dto.getTitle())
        .content(dto.getTitle())
        .writer(dto.getWriter())
        .build();
    }

    default GuestbookDto toDto(Guestbook book){
        return GuestbookDto.builder()
        .gno(book.getGno())
        .title(book.getTitle())
        .content(book.getContent())
        .writer(book.getWriter())
        .build();

    }
}
