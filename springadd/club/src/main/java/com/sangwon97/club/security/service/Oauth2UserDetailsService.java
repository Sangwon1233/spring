package com.sangwon97.club.security.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;

import com.sangwon97.club.entity.Member;
import com.sangwon97.club.entity.MemberRole;
import com.sangwon97.club.repository.MemberRepository;
import com.sangwon97.club.security.dto.AuthMemberDTO;

import jakarta.transaction.Transactional;

@Service
@Log4j2
public class Oauth2UserDetailsService extends DefaultOAuth2UserService {

  @Autowired
  private MemberRepository repository;
  @Autowired
  private PasswordEncoder encoder;
  @Override
  @Transactional
  public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
    log.info(userRequest);
    String clientName =userRequest.getClientRegistration().getClientName();
    Map<?,?> params = userRequest.getAdditionalParameters();
    OAuth2User oAuth2User = super.loadUser(userRequest);
    log.info(clientName);
    log.info(params);
    oAuth2User.getAttributes().forEach((k,v) -> {log.info(k); log.info(v);});

    String email = null;
    if(clientName.equalsIgnoreCase("google")){
      email = oAuth2User.getAttribute("email");

    }
    Member member = saveSociaMember(email);
    
     AuthMemberDTO authMemberDTO = new AuthMemberDTO(member.getEmail(), member.getPassword(), member.getMno(), member.isFromSocial(), member.getName(),member.getRoleSet().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.name())).toList(), oAuth2User.getAttribute("email"));
      return authMemberDTO;
  }

  
  @Transactional
  private Member saveSociaMember(String email){
    Member member =repository.findByEmailAndFromSocial(email, true);

    if(member != null){
      return member;
    }
    Member member2 = member.builder()
    .email(email)
    .password(encoder.encode("1234"))
    .fromSocial(true)
    .build();
    member2.addMemberRole(MemberRole.USER);
    repository.save(member2);

    return member2;
  }
}