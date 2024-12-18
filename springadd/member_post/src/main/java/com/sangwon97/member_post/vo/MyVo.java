package com.sangwon97.member_post.vo;

import java.util.List;

import lombok.Data;

@Data
public class MyVo {
  private String data;
  private List<Post> posts;
}