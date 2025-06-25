package com.techservices.usermanagement.validator.impl;

import org.springframework.stereotype.Service;

import com.techservices.usermanagement.models.requests.CreateUserRequest;
import com.techservices.usermanagement.models.requests.UpdateUserRequest;
import com.techservices.usermanagement.validator.UserManagementValidator;

@Service
public class UserManagementValidatorImpl implements UserManagementValidator {

  @Override
  public void validateCreateRequest(CreateUserRequest request) {

  }

  @Override
  public void validateUserUpdateRequest(UpdateUserRequest request, Long userId) {

  }

}
