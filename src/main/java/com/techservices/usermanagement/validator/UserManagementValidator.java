package com.techservices.usermanagement.validator;

import com.techservices.usermanagement.models.requests.CreateUserRequest;
import com.techservices.usermanagement.models.requests.UpdateUserRequest;

public interface UserManagementValidator {

  void validateCreateRequest(CreateUserRequest request);

  void validateUserUpdateRequest(UpdateUserRequest request, Long userId);

}
