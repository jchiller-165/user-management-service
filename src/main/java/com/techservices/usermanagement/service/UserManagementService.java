package com.techservices.usermanagement.service;

import com.techservices.usermanagement.api.models.reguests.CreateUserRequest;
import com.techservices.usermanagement.api.models.reguests.UpdateUserRequest;
import com.techservices.usermanagement.api.models.responses.UserDetailsResponse;
import com.techservices.usermanagement.api.models.responses.UserUpdateResponse;

public interface UserManagementService {

    UserDetailsResponse createUser(CreateUserRequest request);

    UserUpdateResponse updateUser(UpdateUserRequest request, Long userId);

    Boolean deleteUser(Long userId);

}
