package com.sangwon97.club.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.sangwon97.club.security.dto.handler.LoginSuccessHandler;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Configuration
@EnableMethodSecurity
public class SecurityConfig {
  @Autowired
  private UserDetailsService userDetailsService;
  @Bean
  public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  };

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      http
          .csrf(csrf -> csrf.disable()) // CSRF 비활성화 (필요에 따라 활성화)
          .authorizeHttpRequests(auth -> auth
              .requestMatchers("/sample/all").permitAll()
              .requestMatchers("/sample/member").hasRole("USER")
              // .requestMatchers("/sample/admin").hasRole("ADMIN") 
              .anyRequest().authenticated() // 나머지는 인증 필요
          )
          .formLogin(form -> form.permitAll()) // 기본 로그인 폼 활성화
          .logout(form -> form.logoutUrl("/member/signout"))
          .oauth2Login(o -> o.successHandler(null))
          .rememberMe(r -> r.tokenValiditySeconds(60 * 60 * 24 * 14).userDetailsService(userDetailsService)
          .rememberMeCookieName("remember-id"));//자동 로그인(기억 시간)

      return http.build();
  }
  @Bean
  public LoginSuccessHandler loginSuccessHandler(){
    return new LoginSuccessHandler(passwordEncoder());
  }

}

  