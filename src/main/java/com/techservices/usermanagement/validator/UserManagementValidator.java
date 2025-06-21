package com.techservices.usermanagement.validator;

import com.techservices.usermanagement.api.models.reguests.CreateUserRequest;
import com.techservices.usermanagement.api.models.reguests.UpdateUserRequest;

public interface UserManagementValidator {

    boolean validateCreateRequest(CreateUserRequest request);

    boolean validateUserUpdateRequest(UpdateUserRequest request, Long userId);

}
