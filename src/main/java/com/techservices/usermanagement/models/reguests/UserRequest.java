package com.techservices.usermanagement.models.reguests;

import com.techservices.usermanagement.models.CompanyDetails;
import com.techservices.usermanagement.models.ContactInfo;
import com.techservices.usermanagement.models.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserRequest {

    @NotBlank
    private String username;

    @Email
    @NotBlank
    private String email;

    @NotNull
    private UserRole role;

    @NotNull
    private CompanyDetails companyDetails;

    @NotNull
    private ContactInfo contactInfo;

}
