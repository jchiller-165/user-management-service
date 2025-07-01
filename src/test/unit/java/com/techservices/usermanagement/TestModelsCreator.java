package com.techservices.usermanagement;

import com.techservices.usermanagement.models.requests.RefreshTokenRequest;
import com.techservices.usermanagement.models.requests.UserLoginRequest;
import com.techservices.usermanagement.models.responses.RefreshTokenResponse;
import com.techservices.usermanagement.models.responses.UserLoginResponse;

public class TestModelsCreator {

  public static final String USERNAME = "testuser";
  public static final String PASSWORD = "testpassword";
  public static final String ACCESS_TOKEN = "someBearerToken";
  public static final String REFRESH_TOKEN = "someRefresh";

  public static UserLoginRequest createUserLoginRequest() {
    UserLoginRequest userLoginRequest = new UserLoginRequest();
    userLoginRequest.setUsername(USERNAME);
    userLoginRequest.setPassword(PASSWORD);
    return userLoginRequest;
  }

  public static UserLoginResponse createUserLoginResponse() {
    UserLoginResponse userLoginResponse = new UserLoginResponse();
    userLoginResponse.setAccessToken(ACCESS_TOKEN);
    userLoginResponse.setRefreshToken(REFRESH_TOKEN);
    return userLoginResponse;
  }

  public static RefreshTokenRequest createRefreshTokenRequest() {
    RefreshTokenRequest refreshTokenRequest = new RefreshTokenRequest();
    refreshTokenRequest.setRefreshToken(REFRESH_TOKEN);
    return refreshTokenRequest;
  }

  public static RefreshTokenResponse createRefreshTokenResponse() {
    RefreshTokenResponse refreshTokenResponse = new RefreshTokenResponse();
    refreshTokenResponse.setAccessToken(ACCESS_TOKEN);
    return refreshTokenResponse;
  }

}
