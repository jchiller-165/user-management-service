package com.techservices.usermanagement.repository;

import java.util.Optional;

import com.techservices.usermanagement.repository.entity.AppUserEntity;

public interface UserAuthenticationRepository {

  /**
   * Fetches a user by their username.
   *
   * @param username
   * @return an Optional containing the AppUserEntity if found, or empty if not found
   */
  Optional<AppUserEntity> getUserByUsername(String username);

  /**
   * Saves a new user entity to the database.
   *
   * @param appUserEntity the user entity to save
   * @return the saved AppUserEntity
   */
  Long saveUser(AppUserEntity appUserEntity);

}
