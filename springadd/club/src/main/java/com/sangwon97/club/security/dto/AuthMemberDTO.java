package com.sangwon97.club.security.dto;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Getter
@Setter
@ToString
public class AuthMemberDTO extends User implements OAuth2User{

  private Long mno;
  private String email;
  private String name;
  private boolean fromSocial;
  private Map<String, Object> attr;
  private String pw;

  //oauth2 호출 생성자
  public AuthMemberDTO(String username, String password, Long mno, boolean fromSocial, String name, Collection<? extends GrantedAuthority> authorities
  ,Map<String,Object> attr){
    
    super(username, password, authorities);
    this.mno = mno;
    this.email = username;
    this.fromSocial = fromSocial;
    this.name = name;
    this.attr = attr;
  }
  //security 자체 로그인 호출 생성자
  public AuthMemberDTO (String username, String password,Long mno, Boolean fromSocial, String name,  Collection<? extends GrantedAuthority> authorities){
    super(username, password, authorities);
    this.email = username;
    this.fromSocial = fromSocial;
    this.mno = mno;
    this.name = name;
    pw=password;
  
  }
  @Override
  public Map<String, Object> getAttributes() {
   return attr;
  }
  public boolean getFromSocial() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getFromSocial'");
  }
  
}