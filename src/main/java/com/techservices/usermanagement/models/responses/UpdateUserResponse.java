package com.techservices.usermanagement.models.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateUserResponse {

  private String userId;
  private String message;

}
