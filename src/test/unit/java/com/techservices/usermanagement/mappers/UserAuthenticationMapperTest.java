package com.techservices.usermanagement.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import com.techservices.usermanagement.TestModelsCreator;
import com.techservices.usermanagement.models.UserRole;
import com.techservices.usermanagement.models.requests.RegisterUserRequest;
import com.techservices.usermanagement.repository.entity.AppUserEntity;
import com.techservices.usermanagement.service.mappers.UserAuthenticationMapper;

class UserAuthenticationMapperTest {

  private final UserAuthenticationMapper mapper = Mappers.getMapper(UserAuthenticationMapper.class);

  @Test
  void toAppUserEntity_mapsFieldsCorrectly() {
    RegisterUserRequest request = TestModelsCreator.createRegisterUserRequest();
    AppUserEntity entity = mapper.toAppUserEntity(request);

    assertNotNull(entity);
    assertEquals(request.getUsername(), entity.getUsername());
    assertEquals(request.getPassword(), entity.getPassword());
    assertEquals(UserRole.ADMIN, entity.getUserRole());
  }

}
