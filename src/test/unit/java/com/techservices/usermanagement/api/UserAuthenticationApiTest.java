package com.techservices.usermanagement.api;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techservices.usermanagement.TestModelsCreator;
import com.techservices.usermanagement.models.requests.RefreshTokenRequest;
import com.techservices.usermanagement.models.requests.UserLoginRequest;
import com.techservices.usermanagement.models.responses.RefreshTokenResponse;
import com.techservices.usermanagement.models.responses.UserLoginResponse;
import com.techservices.usermanagement.service.UserAuthenticationService;

@WebMvcTest(UserAuthenticationApi.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class UserAuthenticationApiTest {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private UserAuthenticationService userAuthenticationService;

  @Autowired
  private ObjectMapper objectMapper;

  @TestConfiguration
  static class MockConfig {
    @Bean
    public UserAuthenticationService userAuthenticationService() {
      return org.mockito.Mockito.mock(UserAuthenticationService.class);
    }
  }

  @Test
  void loginUser_returnsOk() throws Exception {
    UserLoginRequest loginRequest = TestModelsCreator.createUserLoginRequest();
    UserLoginResponse expectedResponse = TestModelsCreator.createUserLoginResponse();

    when(userAuthenticationService.authenticateUser(loginRequest)).thenReturn(expectedResponse);

    mockMvc.perform(post("/auth/login").contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(loginRequest))).andExpect(status().isOk())
        .andExpect(content().json(objectMapper.writeValueAsString(expectedResponse)));
  }

  @Test
  void refreshToken_returnsOk() throws Exception {
    RefreshTokenRequest refreshTokenRequest = TestModelsCreator.createRefreshTokenRequest();
    RefreshTokenResponse expectedResponse = TestModelsCreator.createRefreshTokenResponse();

    when(userAuthenticationService.refreshToken(refreshTokenRequest)).thenReturn(expectedResponse);

    mockMvc.perform(post("/auth/refresh").contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(refreshTokenRequest))).andExpect(status().isOk())
        .andExpect(content().json(objectMapper.writeValueAsString(expectedResponse)));
  }

}
