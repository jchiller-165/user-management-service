package com.techservices.usermanagement.models.responses;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RefreshTokenResponse {

  @NotBlank(message = "refreshToken cannot be blank")
  private String accessToken;

}
