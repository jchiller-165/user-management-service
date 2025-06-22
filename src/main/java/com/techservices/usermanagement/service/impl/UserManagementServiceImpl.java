package com.techservices.usermanagement.service.impl;

import com.techservices.usermanagement.models.reguests.CreateUserRequest;
import com.techservices.usermanagement.models.reguests.UpdateUserRequest;
import com.techservices.usermanagement.models.responses.UserCreatedResponse;
import com.techservices.usermanagement.models.responses.UserDetailsResponse;
import com.techservices.usermanagement.models.responses.UserUpdateResponse;
import com.techservices.usermanagement.service.UserManagementService;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public class UserManagementServiceImpl implements UserManagementService {

    @Override
    public UserDetailsResponse getUserById(@NonNull Long userId) {
        return null;
    }

    @Override
    public UserCreatedResponse createUser(@NonNull CreateUserRequest request) {
        return null;
    }

    @Override
    public UserUpdateResponse updateUser(@NonNull UpdateUserRequest request, @NonNull Long userId) {
        return null;
    }

    @Override
    public Boolean deleteUser(@NonNull Long userId) {
        return null;
    }

}
