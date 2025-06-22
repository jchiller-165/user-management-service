package com.techservices.usermanagement.service;

import com.techservices.usermanagement.models.reguests.CreateUserRequest;
import com.techservices.usermanagement.models.reguests.UpdateUserRequest;
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
    UserCreatedResponse createUser(@NonNull CreateUserRequest request);

    /**
     * Updates user details for a user ID.
     *
     * @param userId the ID of the user
     * @return UserUpdateResponse containing user details or null if not found
     * @UpdateUserRequest request containing updated user details
     */
    UserUpdateResponse updateUser(@NonNull UpdateUserRequest request, @NonNull Long userId);

    /**
     * Deletes a user by user ID.
     *
     * @param userId the ID of the user
     * @return true if deletion was successful, false otherwise
     */
    Boolean deleteUser(@NonNull Long userId);

}
