package com.techservices.usermanagement.validator;

import com.techservices.usermanagement.models.reguests.UserRequest;

public interface UserManagementValidator {

    void validateCreateRequest(UserRequest request);

    void validateUserUpdateRequest(UserRequest request, Long userId);

}
