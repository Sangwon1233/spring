package com.sangwon97.guestbook.service;


import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.sangwon97.guestbook.domain.dto.GuestbookDto;
import com.sangwon97.guestbook.domain.dto.GuestbookModifyDTO;
import com.sangwon97.guestbook.domain.dto.GuestbookViewDTO;
import com.sangwon97.guestbook.domain.dto.PageRequestDto;
import com.sangwon97.guestbook.domain.dto.PageResultDto;
import com.sangwon97.guestbook.domain.entity.Guestbook;
import com.sangwon97.guestbook.domain.entity.QGuestbook;

public interface GuestbookService {
  Long writer(GuestbookDto dto);
  PageResultDto<GuestbookDto,Guestbook> list(PageRequestDto dto);
  void modify(GuestbookDto dto);
  void remove(Long gno);
  GuestbookViewDTO get(Long gno);
  GuestbookDto read(Long gno);
  
    //interface를 구체화시키기 static,default
    //dto >> entity 변환 메서드 정의
    default Guestbook toEntity(GuestbookDto dto){
        return Guestbook.builder()
        .gno(dto.getGno())
        .title(dto.getTitle())
        .content(dto.getContent())
        .writer(dto.getWriter())
        .build();
    }
     // entity >> dto 변환 메서드 정의
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
    private BooleanBuilder getSearch(PageRequestDto dto){
        String type = dto.getType();
        BooleanBuilder builder = new BooleanBuilder();
        QGuestbook qGuestbook = QGuestbook.guestbook;
        BooleanExpression expression = qGuestbook.gno.gt(0L);
        builder.and(expression);
        if(type == null || type.trim().isEmpty()){
          return builder;
        }
        
        BooleanBuilder conditionBuilder = new BooleanBuilder();
        String keyword = dto.getKeyword();
        if(type.contains("T")){
          conditionBuilder.or(qGuestbook.title.contains(type));
        }
        if(type.contains("C")){
          conditionBuilder.or(qGuestbook.content.contains(type));
        }
        if(type.contains("W")){
          conditionBuilder.or(qGuestbook.writer.contains(type));
        }
        builder.and(conditionBuilder);
        return builder;
      }
      
}
