package com.techservices.usermanagement.service;

import com.techservices.usermanagement.models.UserDetails;
import com.techservices.usermanagement.models.requests.CreateUserRequest;
import com.techservices.usermanagement.models.requests.UpdateUserRequest;
import com.techservices.usermanagement.models.responses.CreateUserResponse;
import com.techservices.usermanagement.models.responses.UpdateUserResponse;
import lombok.NonNull;

public interface UserManagementService {

    /**
     * Retrieves user details by user ID.
     *
     * @param userId the ID of the user
     * @return UserDetails containing user details or null if not found
     */
    UserDetails getUserById(@NonNull Long userId);


    /**
     * Creates user details for a user.
     *
     * @return UserDetailsResponse containing user details or null if not found
     * @CreateUserRequest request containing new user details
     */
    CreateUserResponse createUser(@NonNull CreateUserRequest request);

    /**
     * Updates user details for a user ID.
     *
     * @param userId the ID of the user
     * @return UserUpdateResponse containing user details or null if not found
     * @UpdateUserRequest request containing updated user details
     */
    UpdateUserResponse updateUser(@NonNull UpdateUserRequest request, @NonNull Long userId);

    /**
     * Deletes a user by user ID.
     *
     * @param userId the ID of the user
     */
    void deleteUser(@NonNull Long userId);

}
