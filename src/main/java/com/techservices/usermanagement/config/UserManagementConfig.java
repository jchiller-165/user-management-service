package com.techservices.usermanagement.config;

import com.techservices.usermanagement.service.mappers.UserDetailsMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserManagementConfig {

  @Bean
  public UserDetailsMapper userDetailsMapper() {
    return Mappers.getMapper(UserDetailsMapper.class);
  }

}
