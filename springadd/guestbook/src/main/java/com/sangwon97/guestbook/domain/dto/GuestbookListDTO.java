package com.sangwon97.guestbook.domain.dto;

import java.time.LocalDateTime;

import com.sangwon97.guestbook.domain.entity.Guestbook;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GuestbookListDTO {
    private Long gno;
    private String title;
    private String writer;
    private LocalDateTime regDate;
    private LocalDateTime modDate;

    public GuestbookListDTO(Guestbook entity){
        gno = entity.getGno();
        title = entity.getTitle();
        writer = entity.getWriter();
        regDate = entity.getRegDate();
        modDate = entity.getModDate();

    }
     // public GuestbookEntitiy toEntitiy(){
  //   return new GuestbookEntitiy().builder().title(title).content(content).writer(writer).build();
  // }
}
