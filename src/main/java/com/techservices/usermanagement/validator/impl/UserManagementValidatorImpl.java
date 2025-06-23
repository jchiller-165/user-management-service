package com.techservices.usermanagement.validator.impl;

import com.techservices.usermanagement.models.reguests.UserRequest;
import com.techservices.usermanagement.validator.UserManagementValidator;
import org.springframework.stereotype.Service;

@Service
public class UserManagementValidatorImpl implements UserManagementValidator {

    @Override
    public void validateCreateRequest(UserRequest request) {

    }

    @Override
    public void validateUserUpdateRequest(UserRequest request, Long userId) {

    }

}
