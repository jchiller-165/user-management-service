package com.techservices.usermanagement.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@ConfigurationProperties(prefix = "security")
public class SecurityConfig {

  private String secret;
  private long accessExpiration;
  private long refreshExpiration;

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(
        auth -> auth.requestMatchers("/auth/login", "/auth/register", "/auth/refresh").permitAll().anyRequest()
            .authenticated());
    return http.build();
  }

  public String getSecret() {
    return secret;
  }

  public void setSecret(String secret) {
    this.secret = secret;
  }

  public long getAccessExpiration() {
    return accessExpiration;
  }

  public void setAccessExpiration(long accessExpiration) {
    this.accessExpiration = accessExpiration;
  }

  public long getRefreshExpiration() {
    return refreshExpiration;
  }

  public void setRefreshExpiration(long refreshExpiration) {
    this.refreshExpiration = refreshExpiration;
  }

}
