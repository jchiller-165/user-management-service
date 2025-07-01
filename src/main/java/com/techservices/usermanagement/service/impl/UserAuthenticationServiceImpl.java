package com.techservices.usermanagement.service.impl;

import static com.techservices.usermanagement.errors.AppErrors.INVALID_CREDENTIALS;
import static com.techservices.usermanagement.errors.AppErrors.INVALID_REFRESH_TOKEN;
import static com.techservices.usermanagement.errors.AppErrors.USER_ALREADY_EXISTS;
import static com.techservices.usermanagement.errors.AppErrors.USER_NOT_FOUND;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.techservices.usermanagement.errors.exceptions.BadRequestException;
import com.techservices.usermanagement.errors.exceptions.NotFoundException;
import com.techservices.usermanagement.models.requests.RefreshTokenRequest;
import com.techservices.usermanagement.models.requests.RegisterUserRequest;
import com.techservices.usermanagement.models.requests.UserLoginRequest;
import com.techservices.usermanagement.models.responses.RefreshTokenResponse;
import com.techservices.usermanagement.models.responses.UserLoginResponse;
import com.techservices.usermanagement.repository.UserAuthenticationRepository;
import com.techservices.usermanagement.repository.entity.AppUserEntity;
import com.techservices.usermanagement.service.UserAuthenticationService;
import com.techservices.usermanagement.service.mappers.UserAuthenticationMapper;
import com.techservices.usermanagement.service.util.JwtService;

@Service
public class UserAuthenticationServiceImpl implements UserAuthenticationService {

  private static final Logger LOGGER = LoggerFactory.getLogger(UserAuthenticationServiceImpl.class);

  @Autowired
  private UserAuthenticationRepository userAuthenticationRepository;
  @Autowired
  private PasswordEncoder passwordEncoder;
  @Autowired
  private JwtService jwtService;
  @Autowired
  private UserAuthenticationMapper userAuthenticationMapper;

  @Override
  public UserLoginResponse authenticateUser(UserLoginRequest loginRequest) {
    final AppUserEntity appUser = userAuthenticationRepository.getUserByUsername(loginRequest.getUsername())
        .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));

    if (!passwordEncoder.matches(loginRequest.getPassword(), appUser.getPassword())) {
      throw new BadRequestException(INVALID_CREDENTIALS);
    }

    final String accessToken = jwtService.generateAccessToken(appUser);
    final String refreshToken = jwtService.generateRefreshToken(appUser);

    return UserLoginResponse.builder().accessToken(accessToken).refreshToken(refreshToken).userId(appUser.getUserId())
        .build();
  }

  @Override
  public RefreshTokenResponse refreshToken(RefreshTokenRequest refreshRequest) {
    final String username = jwtService.extractUsername(refreshRequest.getRefreshToken());
    final AppUserEntity appUser = userAuthenticationRepository.getUserByUsername(username)
        .orElseThrow(() -> new NotFoundException(USER_NOT_FOUND));

    if (!jwtService.isTokenValid(refreshRequest.getRefreshToken(), appUser, "refresh")) {
      throw new BadRequestException(INVALID_REFRESH_TOKEN);
    }
    return RefreshTokenResponse.builder().accessToken(jwtService.generateAccessToken(appUser)).build();
  }

  @Override
  public Long registerUser(RegisterUserRequest registerUserRequest) {
    if (userAuthenticationRepository.getUserByUsername(registerUserRequest.getUsername()).isPresent()) {
      throw new BadRequestException(USER_ALREADY_EXISTS);
    }
    final AppUserEntity appUser = userAuthenticationMapper.toAppUserEntity(registerUserRequest);
    return userAuthenticationRepository.saveUser(appUser);
  }
}
