package com.techservices.usermanagement.validator;

import com.techservices.usermanagement.models.reguests.CreateUserRequest;
import com.techservices.usermanagement.models.reguests.UpdateUserRequest;

public interface UserManagementValidator {

    boolean validateCreateRequest(CreateUserRequest request);

    boolean validateUserUpdateRequest(UpdateUserRequest request, Long userId);

}
