package com.techservices.usermanagement.service;

import com.techservices.usermanagement.models.UserDetails;
import com.techservices.usermanagement.models.requests.CreateUserRequest;
import com.techservices.usermanagement.models.requests.UpdateUserRequest;

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
   * @return Long containing User ID
   * @CreateUserRequest request containing new user details
   */
  Long createUser(@NonNull CreateUserRequest request);

  /**
   * Updates user details for a user ID.
   *
   * @param userId the ID of the user
   * @UpdateUserRequest request containing updated user details
   */
  void updateUser(@NonNull UpdateUserRequest request, @NonNull Long userId);

  /**
   * Deletes a user by user ID.
   *
   * @param userId the ID of the user
   */
  void deleteUser(@NonNull Long userId);

}
