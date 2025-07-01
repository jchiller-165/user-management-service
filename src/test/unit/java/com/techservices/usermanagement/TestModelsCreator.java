package com.techservices.usermanagement;

import com.techservices.usermanagement.models.UserRole;
import com.techservices.usermanagement.models.requests.RefreshTokenRequest;
import com.techservices.usermanagement.models.requests.RegisterUserRequest;
import com.techservices.usermanagement.models.requests.UserLoginRequest;
import com.techservices.usermanagement.models.responses.RefreshTokenResponse;
import com.techservices.usermanagement.models.responses.UserLoginResponse;
import com.techservices.usermanagement.repository.entity.AppUserEntity;

public class TestModelsCreator {

  public static final Long USER_ID = 1L;
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
    userLoginResponse.setUserId(USER_ID);
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

  public static RegisterUserRequest createRegisterUserRequest() {
    RegisterUserRequest registerUserRequest = new RegisterUserRequest();
    registerUserRequest.setUsername(USERNAME);
    registerUserRequest.setPassword(PASSWORD);
    registerUserRequest.setUserRole(UserRole.ADMIN);
    return registerUserRequest;
  }

  public static AppUserEntity createAppUserEntity() {
    AppUserEntity appUserEntity = new AppUserEntity();
    appUserEntity.setUserId(USER_ID);
    appUserEntity.setUsername(USERNAME);
    appUserEntity.setPassword(PASSWORD);
    appUserEntity.setUserRole(UserRole.ADMIN);
    return appUserEntity;
  }

}
