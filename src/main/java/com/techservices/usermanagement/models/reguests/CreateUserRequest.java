package com.techservices.usermanagement.models.reguests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class CreateUserRequest {

    @NotBlank
    @NotEmpty
    private String username;

    @Email
    private String email;

}
