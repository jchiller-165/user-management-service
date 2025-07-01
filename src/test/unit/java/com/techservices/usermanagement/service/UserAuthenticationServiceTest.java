package com.techservices.usermanagement.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.techservices.usermanagement.TestModelsCreator;
import com.techservices.usermanagement.errors.exceptions.BadRequestException;
import com.techservices.usermanagement.errors.exceptions.NotFoundException;
import com.techservices.usermanagement.models.requests.RefreshTokenRequest;
import com.techservices.usermanagement.models.requests.RegisterUserRequest;
import com.techservices.usermanagement.models.requests.UserLoginRequest;
import com.techservices.usermanagement.models.responses.RefreshTokenResponse;
import com.techservices.usermanagement.models.responses.UserLoginResponse;
import com.techservices.usermanagement.repository.UserAuthenticationRepository;
import com.techservices.usermanagement.repository.entity.AppUserEntity;
import com.techservices.usermanagement.service.impl.UserAuthenticationServiceImpl;
import com.techservices.usermanagement.service.mappers.UserAuthenticationMapper;
import com.techservices.usermanagement.service.util.JwtService;

class UserAuthenticationServiceTest {

  @Mock
  private UserAuthenticationRepository userAuthenticationRepository;
  @Mock
  private PasswordEncoder passwordEncoder;
  @Mock
  private JwtService jwtService;
  @Mock
  private UserAuthenticationMapper userAuthenticationMapper;

  @InjectMocks
  private UserAuthenticationServiceImpl userAuthenticationService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void authenticateUser_success() {
    UserLoginRequest loginRequest = TestModelsCreator.createUserLoginRequest();
    AppUserEntity userEntity = TestModelsCreator.createAppUserEntity();

    when(userAuthenticationRepository.getUserByUsername(loginRequest.getUsername())).thenReturn(
        Optional.of(userEntity));
    when(passwordEncoder.matches(loginRequest.getPassword(), userEntity.getPassword())).thenReturn(true);
    when(jwtService.generateAccessToken(userEntity)).thenReturn("access");
    when(jwtService.generateRefreshToken(userEntity)).thenReturn("refresh");

    UserLoginResponse response = userAuthenticationService.authenticateUser(loginRequest);

    assertEquals("access", response.getAccessToken());
    assertEquals("refresh", response.getRefreshToken());
    assertEquals(userEntity.getUserId(), response.getUserId());
  }

  @Test
  void authenticateUser_userNotFound() {
    when(userAuthenticationRepository.getUserByUsername("user")).thenReturn(Optional.empty());
    UserLoginRequest loginRequest = TestModelsCreator.createUserLoginRequest();
    assertThrows(NotFoundException.class, () -> userAuthenticationService.authenticateUser(loginRequest));
  }

  @Test
  void authenticateUser_invalidPassword() {
    AppUserEntity userEntity = TestModelsCreator.createAppUserEntity();
    userEntity.setPassword("hashed");
    when(userAuthenticationRepository.getUserByUsername(userEntity.getUsername())).thenReturn(Optional.of(userEntity));
    when(passwordEncoder.matches("pass", "hashed")).thenReturn(false);
    UserLoginRequest loginRequest = TestModelsCreator.createUserLoginRequest();
    assertThrows(BadRequestException.class, () -> userAuthenticationService.authenticateUser(loginRequest));
  }

  @Test
  void refreshToken_success() {
    RefreshTokenRequest refreshRequest = TestModelsCreator.createRefreshTokenRequest();
    String refreshToken = refreshRequest.getRefreshToken();
    AppUserEntity userEntity = TestModelsCreator.createAppUserEntity();
    String username = userEntity.getUsername();

    when(jwtService.extractUsername(refreshToken)).thenReturn(username);
    when(userAuthenticationRepository.getUserByUsername(username)).thenReturn(Optional.of(userEntity));
    when(jwtService.isTokenValid(refreshToken, userEntity, "refresh")).thenReturn(true);
    when(jwtService.generateAccessToken(userEntity)).thenReturn("newAccess");

    RefreshTokenResponse response = userAuthenticationService.refreshToken(refreshRequest);
    assertEquals("newAccess", response.getAccessToken());
  }

  @Test
  void registerUser_alreadyExists() {
    RegisterUserRequest registerRequest = TestModelsCreator.createRegisterUserRequest();

    when(userAuthenticationRepository.getUserByUsername(registerRequest.getUsername())).thenReturn(
        Optional.of(new AppUserEntity()));
    assertThrows(BadRequestException.class, () -> userAuthenticationService.registerUser(registerRequest));
  }

}
