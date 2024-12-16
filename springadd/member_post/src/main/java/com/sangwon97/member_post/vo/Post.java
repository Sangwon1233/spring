package com.sangwon97.member_post.vo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("post")
public class Post {
	private Long pno; // 게시글 번호
	private String title; // 게시글 제목
	private String writer; // 게시글 작성자
	private String content; // 게시글 내용
	private Long viewCount; // 게시글 조회수
	private Date regdate; // 게시글 등록일
	private Date updatedate;
	private Integer cno;
	private Boolean attachFlag;

	// private Long rootno;
	// private Integer pdepth;
	// private Integer porder;



	private List<Attach> attachs = new ArrayList<>();



}