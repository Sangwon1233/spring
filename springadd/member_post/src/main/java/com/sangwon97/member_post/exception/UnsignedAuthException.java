package com.sangwon97.member_post.exception;

public class UnsignedAuthException extends RuntimeException{
  public UnsignedAuthException(String msg){
    super(msg);
  }
}