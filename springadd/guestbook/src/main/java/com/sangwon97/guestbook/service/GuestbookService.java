package com.sangwon97.guestbook.service;

import java.util.List;

import com.sangwon97.guestbook.domain.dto.GuestbookListDTO;
import com.sangwon97.guestbook.domain.dto.GuestbookModifyDTO;
import com.sangwon97.guestbook.domain.dto.GuestbookViewDTO;
import com.sangwon97.guestbook.domain.dto.GuestbookWriteDTO;

public interface GuestbookService {
    void writer(GuestbookWriteDTO dto);
    void modify(GuestbookModifyDTO dto);
    void remove(Long gno);
    List<GuestbookListDTO> list();
    GuestbookViewDTO get(Long gno);
}
