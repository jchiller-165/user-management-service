package com.techservices.usermanagement.config;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.techservices.usermanagement.service.mappers.UserDetailsMapper;

@Configuration
public class UserManagementConfig {

  @Bean
  public UserDetailsMapper userDetailsMapper() {
    return Mappers.getMapper(UserDetailsMapper.class);
  }

}
