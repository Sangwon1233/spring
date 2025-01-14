package com.sangwon97.club.security.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sangwon97.club.entity.Member;
import com.sangwon97.club.repository.MemberRepository;
import com.sangwon97.club.security.dto.AuthMemberDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class MyMemberUserDetailService implements UserDetailsService{
  @Autowired
  private MemberRepository repository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    log.info(username);
      Member member = repository.findByEmailAndFromSocial(username, false);
      if(member == null){
        throw new UsernameNotFoundException(username);
      }
      log.info(member);
      log.info(member.getEmail());
      log.info(member.getPassword());
      log.info(member.getRoleSet());
      AuthMemberDTO authMemberDTO = new AuthMemberDTO(member.getEmail(), member.getPassword()
      , member.getMno(), member.isFromSocial(), member.getName(),member.getRoleSet().stream().map(role -> new SimpleGrantedAuthority("ROLE_" + role.name())).toList());
      return authMemberDTO;
  }


}