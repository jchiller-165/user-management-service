package com.techservices.usermanagement.service.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techservices.usermanagement.config.SecurityConfig;
import com.techservices.usermanagement.repository.entity.AppUserEntity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtService {

  @Autowired
  private SecurityConfig securityConfig;

  public String generateAccessToken(AppUserEntity appUser) {
    return createToken(appUser, "access", securityConfig.getAccessExpiration());
  }

  public String generateRefreshToken(AppUserEntity appUser) {
    return createToken(appUser, "refresh", securityConfig.getRefreshExpiration());
  }

  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public String extractTokenType(String token) {
    return extractClaim(token, claims -> (String) claims.get("token_type"));
  }

  public Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }

  public boolean isTokenValid(String token, AppUserEntity appUser, String expectedType) {
    final String username = extractUsername(token);
    final String tokenType = extractTokenType(token);
    return (username.equals(appUser.getUsername()) && expectedType.equals(tokenType) && !isTokenExpired(token));
  }

  public <T> T extractClaim(String token, Function<Claims, T> resolver) {
    final Claims claims = extractAllClaims(token);
    return resolver.apply(claims);
  }

  private Claims extractAllClaims(String token) {
    return Jwts.parser().setSigningKey(securityConfig.getSecret()).parseClaimsJws(token).getBody();
  }

  private boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }

  private String createToken(AppUserEntity appUser, String type, long expiration) {
    Map<String, Object> claims = new HashMap<>();
    claims.put("role", appUser.getUserRole().getValue());
    claims.put("userId", appUser.getUserId());
    claims.put("token_type", type);

    return Jwts.builder().setClaims(claims).setSubject(appUser.getUsername()).setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + expiration))
        .signWith(SignatureAlgorithm.HS256, securityConfig.getSecret()).compact();
  }

}
