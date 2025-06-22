package com.techservices.usermanagement.repository;

import com.techservices.usermanagement.repository.entity.UserDetailsEntity;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserManagementRepository extends JpaRepository<UserDetailsEntity, Long> {

    /**
     * Finds a user by their ID.
     *
     * @param userId the ID of the user to find
     * @return the UserEntity if found, or null if not found
     */
    @NonNull
    Optional<UserDetailsEntity> findById(@NonNull Long userId);

    /**
     * Creates a new user in the repository.
     *
     * @param userDetailsEntity the UserEntity to create
     */
    void createUser(@NonNull UserDetailsEntity userDetailsEntity);

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
    boolean deleteUser(@NonNull Long userId);

}
