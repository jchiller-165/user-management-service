package com.techservices.usermanagement.service;

import com.techservices.usermanagement.models.requests.UserLoginRequest;
import com.techservices.usermanagement.models.responses.UserLoginResponse;

public interface UserAuthenticationService {

  /**
   * Authenticates a user based on the provided login request.
   *
   * @param loginRequest the user login request containing username and password
   * @return a UserLoginResponse containing access and refresh tokens if authentication is successful
   */
  UserLoginResponse authenticateUser(UserLoginRequest loginRequest);

}
