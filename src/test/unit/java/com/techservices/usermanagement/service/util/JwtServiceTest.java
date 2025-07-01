package com.techservices.usermanagement.service.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.techservices.usermanagement.TestModelsCreator;
import com.techservices.usermanagement.config.SecurityConfig;
import com.techservices.usermanagement.models.UserRole;
import com.techservices.usermanagement.repository.entity.AppUserEntity;

class JwtServiceTest {

  private JwtService jwtService;
  private SecurityConfig securityConfig;

  private final String secretKey = "testSecretKey1234567890testSecretKey1234567890";
  private final long accessExpiration = 3600L;
  private final long refreshExpiration = 3600L;

  private AppUserEntity user;

  @BeforeEach
  void setUp() throws Exception {
    securityConfig = mock(SecurityConfig.class);
    when(securityConfig.getSecret()).thenReturn(secretKey);
    when(securityConfig.getAccessExpiration()).thenReturn(accessExpiration);
    when(securityConfig.getRefreshExpiration()).thenReturn(refreshExpiration);

    jwtService = new JwtService();

    Field field = JwtService.class.getDeclaredField("securityConfig");
    field.setAccessible(true);
    field.set(jwtService, securityConfig);

    user = TestModelsCreator.createAppUserEntity();
  }

  @Test
  void generateAndValidateAccessToken() {
    String token = jwtService.generateAccessToken(user);

    assertNotNull(token);
    assertEquals(user.getUsername(), jwtService.extractUsername(token));
    assertEquals("access", jwtService.extractTokenType(token));
    assertEquals(user.getUserId(), jwtService.extractClaim(token, claims -> claims.get("userId", Long.class)));
    assertEquals(UserRole.ADMIN.getValue(), jwtService.extractClaim(token, claims -> claims.get("role", String.class)));

    assertTrue(jwtService.isTokenValid(token, user, "access"));
    assertFalse(jwtService.isTokenValid(token, user, "refresh"));
  }

  @Test
  void generateAndValidateRefreshToken() {
    String token = jwtService.generateRefreshToken(user);

    assertNotNull(token);
    assertEquals(user.getUsername(), jwtService.extractUsername(token));
    assertEquals("refresh", jwtService.extractTokenType(token));
    assertTrue(jwtService.isTokenValid(token, user, "refresh"));
    assertFalse(jwtService.isTokenValid(token, user, "access"));
  }

  @Test
  void extractExpiration_returnsFutureDate() {
    String token = jwtService.generateAccessToken(user);
    Date expiration = jwtService.extractExpiration(token);
    assertTrue(expiration.after(new Date()));
  }

}
