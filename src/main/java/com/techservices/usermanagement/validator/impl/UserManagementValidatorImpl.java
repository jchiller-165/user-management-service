package com.techservices.usermanagement.validator.impl;

import com.techservices.usermanagement.models.reguests.CreateUserRequest;
import com.techservices.usermanagement.models.reguests.UpdateUserRequest;
import com.techservices.usermanagement.validator.UserManagementValidator;
import org.springframework.stereotype.Service;

@Service
public class UserManagementValidatorImpl implements UserManagementValidator {

    @Override
    public boolean validateCreateRequest(CreateUserRequest request) {
        return true;
    }

    @Override
    public boolean validateUserUpdateRequest(UpdateUserRequest request, Long userId) {
        return true;
    }

}
