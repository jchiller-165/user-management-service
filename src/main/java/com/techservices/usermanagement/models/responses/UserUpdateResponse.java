package com.techservices.usermanagement.models.responses;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserUpdateResponse {

    private Boolean success;
    private Long userId;

}
