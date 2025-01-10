package com.sangwon97.club.security.handler;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


import com.sangwon97.club.security.dto.AuthMemberDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
  private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
  
  private PasswordEncoder encoder;

  public LoginSuccessHandler(PasswordEncoder encoder){
    this.encoder=encoder;
  }

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
    Authentication authentication) throws IOException, ServletException {
    log.info("+++++++++++++++++++++++++++++++++");  
    log.info("on autenticated");
    
    AuthMemberDTO authMember = ((AuthMemberDTO)authentication.getPrincipal());
    boolean fromSocial = authMember.getFromSocial();
    boolean PasswordResult = encoder.matches("1234",authMember.getPw());
    log.info(fromSocial);
    log.info(PasswordResult);
    if(fromSocial && PasswordResult){
      redirectStrategy.sendRedirect(request, response, "/member/modify?from=social");
    }  
  }
  
  
}
