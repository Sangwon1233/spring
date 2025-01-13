package com.sangwon97.club.security.util;

import java.time.ZonedDateTime;
import java.util.Date;

import javax.crypto.SecretKey;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;


@Log4j2
public class JWTUtil {
  private String secrekey="sangwon12345678900sangwon12345678900sangwon12345678900";

  // private long expire = 60 * 24 * 30 ;

  private SecretKey key = Keys.hmacShaKeyFor(secrekey.getBytes());

  public String generateToken(String content) throws Exception{
    return Jwts.builder()
    .issuedAt(new Date())
    .expiration(Date.from(ZonedDateTime.now().plusMonths(1L).toInstant()))
    .claim("sub", content)
    // .signWith(null, secrekry.getBytes("utf-8"))
    .signWith(key)
    .compact();
  }
  public String validateExtract(String tokenStr){
    String contentValue = null;
    // Jwts.parser().verifyWith(key).build().parse(tokenStr);
    try{
      // DefaultJws<?> defaultJws = Jwts.parser().setSigningKey(secrekry.getBytes("utf-8").build().p);
      Jws<Claims> defaultJws = Jwts.parser().verifyWith(key).build().parseSignedClaims(tokenStr);
      Claims clains = defaultJws.getPayload();
      log.info(clains.getSubject());//작성자
      log.info(clains.getIssuer()); //토큰 발급 시간
      log.info(clains.getExpiration()); //토큰 만료 시간
      contentValue = (clains.getSubject()); 

    }
    catch(Exception e){
      e.printStackTrace();
      log.info("message");
  
    }
        return contentValue;
  }
  
}
