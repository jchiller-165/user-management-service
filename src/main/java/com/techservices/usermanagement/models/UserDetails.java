package com.techservices.usermanagement.models;

import com.techservices.usermanagement.repository.entity.UserRoleEntity;
import lombok.Data;

@Data
public class UserDetails {

    private String username;
    private String email;
    private UserRoleEntity role;
    private CompanyDetails companyDetails;
    private ContactInfo contactInfo;

}
