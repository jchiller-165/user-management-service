package com.techservices.usermanagement;

import com.techservices.usermanagement.models.reguests.CreateUserRequest;

public class TestModelsCreator {

    public static CreateUserRequest createUserRequest() {
        CreateUserRequest request = new CreateUserRequest();
        request.setUsername("someUsername");
        request.setEmail("someEmail@email.com");
        return request;
    }
}
