package com.techservices.usermanagement.service.impl;

import com.techservices.usermanagement.models.requests.CreateUserRequest;
import com.techservices.usermanagement.models.responses.CreateUserResponse;
import com.techservices.usermanagement.models.requests.UpdateUserRequest;
import com.techservices.usermanagement.models.responses.UpdateUserResponse;
import com.techservices.usermanagement.models.UserDetails;
import com.techservices.usermanagement.service.UserManagementService;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public class UserManagementServiceImpl implements UserManagementService {

    @Override
    public UserDetails getUserById(@NonNull Long userId) {
        return null;
    }

    @Override
    public CreateUserResponse createUser(@NonNull CreateUserRequest request) {
        return null;
    }

    @Override
    public UpdateUserResponse updateUser(@NonNull UpdateUserRequest request, @NonNull Long userId) {
        return null;
    }

    @Override
    public void deleteUser(@NonNull Long userId) {

    }

}
