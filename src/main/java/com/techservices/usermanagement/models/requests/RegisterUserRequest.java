package com.techservices.usermanagement.models.requests;

import com.techservices.usermanagement.models.UserRole;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterUserRequest {

  @NotBlank(message = "Username cannot be blank")
  private String username;
  @NotBlank(message = "Password cannot be blank")
  private String password;

  private UserRole userRole;

}
