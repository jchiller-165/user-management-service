package com.techservices.usermanagement.api;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techservices.usermanagement.TestModelsCreator;
import com.techservices.usermanagement.errors.exceptions.BadRequestException;
import com.techservices.usermanagement.errors.exceptions.NotFoundException;
import com.techservices.usermanagement.models.requests.CreateUserRequest;
import com.techservices.usermanagement.models.requests.UpdateUserRequest;
import com.techservices.usermanagement.models.responses.UpdateUserResponse;
import com.techservices.usermanagement.service.UserManagementService;
import com.techservices.usermanagement.validator.UserManagementValidator;

@WebMvcTest(UserManagementApi.class)
@ExtendWith(MockitoExtension.class)
class UserManagementApiTest {

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private UserManagementService userManagementService;
  @Autowired
  private UserManagementValidator userManagementValidator;
  @Autowired
  private ObjectMapper objectMapper;

  @TestConfiguration
  static class MockConfig {
    @Bean
    public UserManagementService userManagementService() {
      return org.mockito.Mockito.mock(UserManagementService.class);
    }

    @Bean
    public UserManagementValidator userManagementValidator() {
      return org.mockito.Mockito.mock(UserManagementValidator.class);
    }
  }

  @Test
  void getUserById_notFound() throws Exception {
    doThrow(new NotFoundException("Test Message")).when(userManagementService).getUserById(anyLong());
    mockMvc.perform(get("/api/v1/users/1")).andExpect(status().isNotFound());
  }

  @Test
  void createUser_success() throws Exception {
    CreateUserRequest request = TestModelsCreator.createUserCreateRequest();
    doNothing().when(userManagementValidator).validateCreateRequest(any());
    Mockito.when(userManagementService.createUser(any())).thenReturn(123L);

    mockMvc.perform(
            post("/api/v1/users").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isCreated()).andExpect(header().string("Location", "http://localhost/api/v1/users/123"));
  }

  @Test
  void createUser_failValidation() throws Exception {
    CreateUserRequest request = TestModelsCreator.createUserCreateRequest();
    doThrow(new BadRequestException("Test Message")).when(userManagementValidator).validateCreateRequest(request);

    mockMvc.perform(
            post("/api/v1/users").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isBadRequest());
  }

  @Test
  void createUser_invalidRequest() throws Exception {
    CreateUserRequest request = TestModelsCreator.createUserCreateRequest();
    request.setUsername(""); // Invalid username
    doNothing().when(userManagementValidator).validateCreateRequest(any());

    mockMvc.perform(
            post("/api/v1/users").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(request)))
        .andExpect(status().isBadRequest());
  }

  @Test
  void updateUser_notFound() throws Exception {
    UpdateUserRequest request = TestModelsCreator.createUpdateUserRequest();
    doNothing().when(userManagementValidator).validateUserUpdateRequest(any(), anyLong());
    doThrow(new NotFoundException("Test Message")).when(userManagementService).updateUser(any(), anyLong());

    mockMvc.perform(put("/api/v1/users/1").contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(request))).andExpect(status().isNotFound());
  }

  @Test
  void updateUser_success() throws Exception {
    UpdateUserRequest request = TestModelsCreator.createUpdateUserRequest();
    doNothing().when(userManagementValidator).validateUserUpdateRequest(any(), anyLong());
    Mockito.when(userManagementService.updateUser(any(), anyLong()))
        .thenReturn(new UpdateUserResponse("userId", "Updated User"));

    mockMvc.perform(put("/api/v1/users/1").contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(request))).andExpect(status().isOk());
  }

  @Test
  void updateUser_failValidation() throws Exception {
    UpdateUserRequest request = TestModelsCreator.createUpdateUserRequest();
    doThrow(new BadRequestException("Test Message")).when(userManagementValidator)
        .validateUserUpdateRequest(any(), anyLong());

    mockMvc.perform(put("/api/v1/users/1").contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(request))).andExpect(status().isBadRequest());
  }

  @Test
  void updateUser_invalidRequest() throws Exception {
    UpdateUserRequest request = TestModelsCreator.createUpdateUserRequest();
    request.setUsername(""); // Invalid username
    doNothing().when(userManagementValidator).validateUserUpdateRequest(any(), anyLong());

    mockMvc.perform(put("/api/v1/users/1").contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(request))).andExpect(status().isBadRequest());
  }

  @Test
  void deleteUser_notFound() throws Exception {
    doThrow(new NotFoundException("Test Message")).when(userManagementService).deleteUser(anyLong());

    mockMvc.perform(delete("/api/v1/users/1")).andExpect(status().isNotFound());
  }

  @Test
  void deleteUser_success() throws Exception {
    doNothing().when(userManagementService).deleteUser(anyLong());

    mockMvc.perform(delete("/api/v1/users/1")).andExpect(status().isNoContent());
  }

}
