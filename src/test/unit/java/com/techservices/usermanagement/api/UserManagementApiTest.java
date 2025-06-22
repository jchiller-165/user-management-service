package com.techservices.usermanagement.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.techservices.usermanagement.TestModelsCreator;
import com.techservices.usermanagement.models.reguests.CreateUserRequest;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
    void createUser_failValidation() throws Exception {
        CreateUserRequest request = TestModelsCreator.createUserRequest();
        Mockito.when(userManagementValidator.validateCreateRequest(any()))
                .thenReturn(false);

        mockMvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createUser_invalidRequest() throws Exception {
        CreateUserRequest request = TestModelsCreator.createUserRequest();
        request.setUsername(""); // Invalid username
        Mockito.when(userManagementValidator.validateCreateRequest(any()))
                .thenReturn(true);

        mockMvc.perform(post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }
}
