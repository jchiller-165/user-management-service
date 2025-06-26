package com.techservices.usermanagement.repository;

import java.util.Optional;

import com.techservices.usermanagement.repository.entity.UserDetailsEntity;

import lombok.NonNull;

public interface UserManagementRepository {

  /**
   * Finds a user by their ID.
   *
   * @param userId the ID of the user to find
   * @return the UserEntity if found, or null if not found
   */
  Optional<UserDetailsEntity> findUserById(@NonNull Long userId);

  /**
   * Creates a new user in the repository.
   *
   * @param userDetailsEntity the UserEntity to create
   */
  Long createUser(@NonNull UserDetailsEntity userDetailsEntity);

  /**
   * Updates an existing user in the repository.
   *
   * @param userDetailsEntity the UserEntity to update
   */
  void updateUser(@NonNull UserDetailsEntity userDetailsEntity);

  /**
   * Deletes a user by their ID.
   *
   * @param userId the ID of the user to delete
   */
  void deleteUser(@NonNull Long userId);

}
