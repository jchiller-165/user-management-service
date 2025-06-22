package com.techservices.usermanagement.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techservices.usermanagement.TestModelsCreator;
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
        Mockito.when(userManagementService.getUserById(1L))
                .thenReturn(null);

        mockMvc.perform(get("/api/v1/users/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void createUser_success() throws Exception {
        UserRequest request = TestModelsCreator.createUserRequest();
        Mockito.when(userManagementValidator.validateCreateRequest(any()))
                .thenReturn(true);
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
        Mockito.when(userManagementValidator.validateCreateRequest(any()))
                .thenReturn(false);

        mockMvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createUser_invalidRequest() throws Exception {
        UserRequest request = TestModelsCreator.createUserRequest();
        request.setUsername(""); // Invalid username
        Mockito.when(userManagementValidator.validateCreateRequest(any()))
                .thenReturn(true);

        mockMvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateUser_notFound() throws Exception {
        UserRequest request = TestModelsCreator.createUserRequest();
        Mockito.when(userManagementValidator.validateUserUpdateRequest(any(), any()))
                .thenReturn(true);
        Mockito.when(userManagementService.updateUser(any(), any()))
                .thenReturn(null);

        mockMvc.perform(put("/api/v1/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateUser_success() throws Exception {
        UserRequest request = TestModelsCreator.createUserRequest();
        Mockito.when(userManagementValidator.validateUserUpdateRequest(any(), any()))
                .thenReturn(true);
        Mockito.when(userManagementService.updateUser(any(), any()))
                .thenReturn(new UserUpdateResponse(true, 1L));

        mockMvc.perform(put("/api/v1/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    void updateUser_failValidation() throws Exception {
        UserRequest request = TestModelsCreator.createUserRequest();
        Mockito.when(userManagementValidator.validateUserUpdateRequest(any(), any()))
                .thenReturn(false);

        mockMvc.perform(put("/api/v1/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updateUser_invalidRequest() throws Exception {
        UserRequest request = TestModelsCreator.createUserRequest();
        request.setUsername(""); // Invalid username
        Mockito.when(userManagementValidator.validateUserUpdateRequest(any(), any()))
                .thenReturn(true);

        mockMvc.perform(put("/api/v1/users/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteUser_notFound() throws Exception {
        Mockito.when(userManagementService.deleteUser(1L))
                .thenReturn(false);

        mockMvc.perform(delete("/api/v1/users/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteUser_success() throws Exception {
        Mockito.when(userManagementService.deleteUser(1L))
                .thenReturn(true);

        mockMvc.perform(delete("/api/v1/users/1"))
                .andExpect(status().isOk());
    }
}
