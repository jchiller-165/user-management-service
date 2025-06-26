package com.techservices.usermanagement.config;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.techservices.usermanagement.service.mappers.UserDetailsMapper;

class AppConfigTest {

  @Test
  void testBeanCreation() {
    var context = new AnnotationConfigApplicationContext(UserManagementConfig.class);
    assertNotNull(context.getBean(UserDetailsMapper.class));
    context.close();
  }

}
