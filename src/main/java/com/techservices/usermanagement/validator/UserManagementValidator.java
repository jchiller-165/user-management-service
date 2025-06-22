package com.techservices.usermanagement.validator;

import com.techservices.usermanagement.models.reguests.UserRequest;

public interface UserManagementValidator {

    boolean validateCreateRequest(UserRequest request);

    boolean validateUserUpdateRequest(UserRequest request, Long userId);

}
