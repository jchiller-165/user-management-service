package com.techservices.usermanagement.service.impl;

import com.techservices.usermanagement.api.models.reguests.CreateUserRequest;
import com.techservices.usermanagement.api.models.reguests.UpdateUserRequest;
import com.techservices.usermanagement.api.models.responses.UserDetailsResponse;
import com.techservices.usermanagement.api.models.responses.UserUpdateResponse;
import com.techservices.usermanagement.service.UserManagementService;
import org.springframework.stereotype.Service;

@Service
public class UserManagementServiceImpl implements UserManagementService {

    @Override
    public UserDetailsResponse createUser(CreateUserRequest request) {
        return null;
    }

    @Override
    public UserUpdateResponse updateUser(UpdateUserRequest request, Long userId) {
        return null;
    }

    @Override
    public Boolean deleteUser(Long userId) {
        return null;
    }

}
