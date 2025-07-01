package com.techservices.usermanagement.models.responses;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginResponse {

  @NotBlank(message = "userId cannot be blank")
  private Long userId;
  @NotBlank(message = "accessToken cannot be blank")
  private String accessToken;
  @NotBlank(message = "refreshToken cannot be blank")
  private String refreshToken;

}
