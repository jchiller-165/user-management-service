package com.techservices.usermanagement.models.requests;

import com.techservices.usermanagement.models.UserRole;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateUserRequest {

  @NotBlank(message = "Username is required")
  private String username;
  private UserRole.RoleTypeEnum userRole;
  @Valid
  private ContactInfo contactInfo;
  @Valid
  private CompanyDetails companyDetails;

  @Data
  public static class ContactInfo {
    @NotBlank
    @Email
    private String email;
    @NotBlank(message = "First Name is required")
    private String firstName;
    @NotBlank(message = "Last Name is required")
    private String lastName;
    @NotBlank(message = "Phone number is required")
    private String phoneNumber;
    @NotBlank(message = "Address is required")
    private String address;
    @NotBlank(message = "City is required")
    private String city;
    @NotBlank(message = "State is required")
    private String state;
    @NotBlank(message = "Postal code is required")
    private String postalCode;
    @NotBlank(message = "Country is required")
    private String country;
  }

  @Data
  public static class CompanyDetails {
    @NotBlank(message = "Company name is required")
    private String companyName;
    @NotBlank(message = "Company address is required")
    private String address;
    @NotBlank(message = "Company city is required")
    private String city;
    @NotBlank(message = "Company state is required")
    private String state;
    @NotBlank(message = "Company postal code is required")
    private String postalCode;
    @NotBlank(message = "Company country is required")
    private String country;
  }

}
