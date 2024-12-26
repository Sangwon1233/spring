package com.sangwon97.guestbook.domain.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@MappedSuperclass //이 클래스가 모든 클래스의 조상
@EntityListeners(value = AuditingEntityListener.class)//어플리케이션 시작(메인)
@Getter
public class BaseEntity {
    
    @CreatedDate
    @Column(name = "regdate", updatable = false) //칼럼 이름 정해주기 , 내부적 수정 금지
    private LocalDateTime regDate;

    @LastModifiedDate
    @Column(name = "moddate")
    private LocalDateTime modDate;
}