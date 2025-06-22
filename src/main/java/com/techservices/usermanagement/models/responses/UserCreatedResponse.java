package com.techservices.usermanagement.models.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserCreatedResponse {

    private Boolean success;
    private Long userId;

}
