package com.techservices.usermanagement.models;

import lombok.Data;

@Data
public class UserDetails {

    private Long userId;
    private String username;
    private String firstName;
    private String lastName;
    private UserRole.RoleTypeEnum userRole;
    private ContactInfo contactInfo;
    private CompanyDetails companyDetails;

}
