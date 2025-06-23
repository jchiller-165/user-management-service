package com.techservices.usermanagement.service;

import com.techservices.usermanagement.models.reguests.UserRequest;
import com.techservices.usermanagement.models.responses.UserCreatedResponse;
import com.techservices.usermanagement.models.responses.UserDetailsResponse;
import com.techservices.usermanagement.models.responses.UserUpdateResponse;
import lombok.NonNull;

public interface UserManagementService {

    /**
     * Retrieves user details by user ID.
     *
     * @param userId the ID of the user
     * @return UserDetailsResponse containing user details or null if not found
     */
    UserDetailsResponse getUserById(@NonNull Long userId);


    /**
     * Creates user details for a user.
     *
     * @return UserDetailsResponse containing user details or null if not found
     * @CreateUserRequest request containing new user details
     */
    UserCreatedResponse createUser(@NonNull UserRequest request);

    /**
     * Updates user details for a user ID.
     *
     * @param userId the ID of the user
     * @return UserUpdateResponse containing user details or null if not found
     * @UpdateUserRequest request containing updated user details
     */
    UserUpdateResponse updateUser(@NonNull UserRequest request, @NonNull Long userId);

    /**
     * Deletes a user by user ID.
     *
     * @param userId the ID of the user
     */
    void deleteUser(@NonNull Long userId);

}
