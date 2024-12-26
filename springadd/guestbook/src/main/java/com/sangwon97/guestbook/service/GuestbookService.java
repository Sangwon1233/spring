package com.sangwon97.guestbook.service;

import java.util.List;


import com.sangwon97.guestbook.domain.dto.GuestbookDto;
import com.sangwon97.guestbook.domain.dto.GuestbookListDTO;
import com.sangwon97.guestbook.domain.dto.GuestbookModifyDTO;
import com.sangwon97.guestbook.domain.dto.GuestbookViewDTO;
import com.sangwon97.guestbook.domain.dto.PageRequestDto;
import com.sangwon97.guestbook.domain.dto.PageResultDto;
import com.sangwon97.guestbook.domain.entity.Guestbook;

public interface GuestbookService {
    Long writer(GuestbookDto dto);
    PageResultDto<GuestbookDto,Guestbook> list(PageRequestDto dto);

    void modify(GuestbookModifyDTO dto);

    void remove(Long gno);

    GuestbookViewDTO get(Long gno);
    //interface를 구체화시키기 static,default
    default Guestbook toEntity(GuestbookDto dto){
        return Guestbook.builder()
        .gno(dto.getGno())
        .title(dto.getTitle())
        .content(dto.getContent())
        .writer(dto.getWriter())
        .build();
    }

    default GuestbookDto toDto(Guestbook book){
        return GuestbookDto.builder()
        .gno(book.getGno())
        .title(book.getTitle())
        .content(book.getContent())
        .writer(book.getWriter())
        .regDate(book.getRegDate())
        .modDate(book.getModDate())
        .build();

    }
}
