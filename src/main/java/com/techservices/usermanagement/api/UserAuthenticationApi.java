package com.techservices.usermanagement.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techservices.usermanagement.models.requests.RefreshTokenRequest;
import com.techservices.usermanagement.models.requests.UserLoginRequest;
import com.techservices.usermanagement.models.responses.RefreshTokenResponse;
import com.techservices.usermanagement.models.responses.UserLoginResponse;
import com.techservices.usermanagement.service.UserAuthenticationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
@Tag(name = "User Authentication", description = "APIs for user authentication and management")
public class UserAuthenticationApi {

  @Autowired
  UserAuthenticationService userAuthenticationService;

  @Operation(summary = "Login")
  @PostMapping("/login")
  public ResponseEntity<UserLoginResponse> loginUser(@Valid @RequestBody UserLoginRequest loginRequest) {
    final UserLoginResponse userLoginResponse = userAuthenticationService.authenticateUser(loginRequest);
    return ResponseEntity.ok(userLoginResponse);
  }

  @Operation(summary = "Refresh Token")
  @PostMapping("/refresh")
  public ResponseEntity<RefreshTokenResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest refreshRequest) {
    final RefreshTokenResponse refreshResponse = userAuthenticationService.refreshToken(refreshRequest);
    return ResponseEntity.ok(refreshResponse);
  }

}
