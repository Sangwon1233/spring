package com.sangwon97.mongo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.sangwon97.mongo.handler.ChatHander;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

  @Override
  public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
    
    registry.addHandler(chatHander(),"/chat").setAllowedOrigins("*");
    
  }
  

  @Bean
  public ChatHander chatHander(){
    return new ChatHander();
  }
}
