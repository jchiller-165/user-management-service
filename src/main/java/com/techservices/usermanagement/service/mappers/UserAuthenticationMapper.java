package com.techservices.usermanagement.service.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.techservices.usermanagement.models.UserRole;
import com.techservices.usermanagement.models.requests.RegisterUserRequest;
import com.techservices.usermanagement.repository.entity.AppUserEntity;

@Mapper(componentModel = "spring", uses = { UserRole.class })
public abstract class UserAuthenticationMapper {

  /**
   * Converts a RegisterUserRequest to an AppUserEntity.
   *
   * @param registerUserRequest the request containing user details
   * @return an AppUserEntity populated with the details from the request
   */
  @Mapping(source = "username", target = "username")
  @Mapping(source = "password", target = "password")
  @Mapping(source = "userRole", target = "userRole")
  public abstract AppUserEntity toAppUserEntity(RegisterUserRequest registerUserRequest);

}
