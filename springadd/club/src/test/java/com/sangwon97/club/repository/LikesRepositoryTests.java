package com.sangwon97.club.repository;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sangwon97.club.entity.Likes;
import com.sangwon97.club.entity.Member;
import com.sangwon97.club.entity.Note;

import lombok.extern.log4j.Log4j2;

@Log4j2
@SpringBootTest
public class LikesRepositoryTests {

    @Autowired
    private LikesRepository repository;

    @Test
    public void testExist() {
        log.info(repository);
    }

//     @Test
//     public void testInsert() {
//         Likes likes = Likes.builder()
//             .member(Member.builder().mno(100L).build())
//             .note(Note.builder().num(1L).build())
//             .build();
//         repository.save(likes);
//     }

//     @Test
//     public void testDelete() {
//         repository.delete(Likes.builder()
//             .member(Member.builder().mno(100L).build())
//             .note(Note.builder().num(1L).build())
//             .build());
//     }
 }
