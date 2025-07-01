package com.techservices.usermanagement.service;

import com.techservices.usermanagement.models.requests.RefreshTokenRequest;
import com.techservices.usermanagement.models.requests.UserLoginRequest;
import com.techservices.usermanagement.models.responses.RefreshTokenResponse;
import com.techservices.usermanagement.models.responses.UserLoginResponse;

public interface UserAuthenticationService {

  /**
   * Authenticates a user based on the provided login request.
   *
   * @param loginRequest the user login request containing username and password
   * @return a UserLoginResponse containing access and refresh tokens if authentication is successful
   */
  UserLoginResponse authenticateUser(UserLoginRequest loginRequest);

  /**
   * Refreshes the authentication token based on the provided refresh token request.
   *
   * @param refreshRequest the request containing the refresh token
   * @return a RefreshTokenResponse containing new access and refresh tokens if the refresh is successful
   */
  RefreshTokenResponse refreshToken(RefreshTokenRequest refreshRequest);

}
