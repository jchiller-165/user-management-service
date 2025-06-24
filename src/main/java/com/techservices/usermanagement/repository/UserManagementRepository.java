package com.techservices.usermanagement.repository;

import com.techservices.usermanagement.repository.entity.UserDetailsEntity;
import lombok.NonNull;

import java.util.Optional;

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
    boolean updateUser(@NonNull UserDetailsEntity userDetailsEntity);

    /**
     * Deletes a user by their ID.
     *
     * @param userId the ID of the user to delete
     */
    boolean deleteUser(@NonNull Long userId);

}
