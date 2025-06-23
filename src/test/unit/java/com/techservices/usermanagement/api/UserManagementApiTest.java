package com.techservices.usermanagement.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techservices.usermanagement.TestModelsCreator;
import com.techservices.usermanagement.errors.exceptions.BadRequestException;
import com.techservices.usermanagement.errors.exceptions.NotFoundException;
import com.techservices.usermanagement.models.reguests.UserRequest;
import com.techservices.usermanagement.models.responses.UserCreatedResponse;
import com.techservices.usermanagement.models.responses.UserUpdateResponse;
import com.techservices.usermanagement.service.UserManagementService;
import com.techservices.usermanagement.validator.UserManagementValidator;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
        doThrow(new NotFoundException("Test Message")).
                when(userManagementService).getUserById(anyLong());

        mockMvc.perform(get("/api/v1/users/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void createUser_success() throws Exception {
        UserRequest request = TestModelsCreator.createUserRequest();

        doNothing().when(userManagementValidator).validateCreateRequest(any());

        Mockito.when(userManagementService.createUser(any()))
                .thenReturn(new UserCreatedResponse(true, 1L));

        mockMvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    void createUser_failValidation() throws Exception {
        UserRequest request = TestModelsCreator.createUserRequest();

        doThrow(new BadRequestException("Test Message")).
                when(userManagementValidator).validateCreateRequest(request);

        mockMvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createUser_invalidRequest() throws Exception {
        UserRequest request = TestModelsCreator.createUserRequest();
        request.setUsername(""); // Invalid username

        doNothing().when(userManagementValidator).validateCreateRequest(any());

        mockMvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateUser_notFound() throws Exception {
        UserRequest request = TestModelsCreator.createUserRequest();

        doNothing().when(userManagementValidator).validateUserUpdateRequest(any(), anyLong());

        doThrow(new NotFoundException("Test Message")).
                when(userManagementService).updateUser(any(), anyLong());

        mockMvc.perform(put("/api/v1/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateUser_success() throws Exception {
        UserRequest request = TestModelsCreator.createUserRequest();

        doNothing().when(userManagementValidator).validateUserUpdateRequest(any(), anyLong());

        Mockito.when(userManagementService.updateUser(any(), anyLong()))
                .thenReturn(new UserUpdateResponse(true, 1L));

        mockMvc.perform(put("/api/v1/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    void updateUser_failValidation() throws Exception {
        UserRequest request = TestModelsCreator.createUserRequest();

        doThrow(new BadRequestException("Test Message")).
                when(userManagementValidator).validateUserUpdateRequest(any(), anyLong());

        mockMvc.perform(put("/api/v1/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateUser_invalidRequest() throws Exception {
        UserRequest request = TestModelsCreator.createUserRequest();
        request.setUsername(""); // Invalid username

        doNothing().when(userManagementValidator).validateUserUpdateRequest(any(), anyLong());

        mockMvc.perform(put("/api/v1/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteUser_notFound() throws Exception {
        doThrow(new NotFoundException("Test Message")).
                when(userManagementService).deleteUser(anyLong());

        mockMvc.perform(delete("/api/v1/users/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteUser_success() throws Exception {
        doNothing().when(userManagementService).deleteUser(anyLong());

        mockMvc.perform(delete("/api/v1/users/1"))
                .andExpect(status().isNoContent());
    }
}
