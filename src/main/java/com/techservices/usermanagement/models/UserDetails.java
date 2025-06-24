package com.techservices.usermanagement.models;

import lombok.Data;

@Data
public class UserDetails {

  private Long userId;
  private String username;
  private UserRole.RoleTypeEnum userRole;
  private ContactInfo contactInfo;
  private CompanyDetails companyDetails;

}
