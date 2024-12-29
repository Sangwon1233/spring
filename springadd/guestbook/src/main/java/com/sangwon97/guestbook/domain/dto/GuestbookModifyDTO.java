package com.sangwon97.guestbook.domain.dto;

import com.sangwon97.guestbook.domain.entity.Guestbook;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GuestbookModifyDTO {
    private Long gno;
    private String title;
    private String content;
    private String writer;

    public Guestbook toEntity(){
        return new Guestbook().builder().gno(gno).title(title).content(content).writer(writer).build();
    }
}
