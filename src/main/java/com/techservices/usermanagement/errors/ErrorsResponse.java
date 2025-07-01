package com.techservices.usermanagement.errors;

import lombok.Data;

@Data
public class ErrorsResponse {

  private int status;
  private String message;

  public ErrorsResponse(int status, String message) {
    this.status = status;
    this.message = message;
  }

}
