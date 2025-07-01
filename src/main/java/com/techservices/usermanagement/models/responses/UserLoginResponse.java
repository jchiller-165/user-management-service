package com.techservices.usermanagement.models.responses;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserLoginResponse {

  @NotBlank(message = "accessToken cannot be blank")
  private String accessToken;
  @NotBlank(message = "refreshToken cannot be blank")
  private String refreshToken;

}
