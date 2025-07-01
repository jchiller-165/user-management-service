package com.techservices.usermanagement.config;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.techservices.usermanagement.service.mappers.UserAuthenticationMapper;

@Configuration
public class UserManagementConfig {

  @Bean
  public UserAuthenticationMapper getUserAuthenticationMapper() {
    return Mappers.getMapper(UserAuthenticationMapper.class);
  }

}
