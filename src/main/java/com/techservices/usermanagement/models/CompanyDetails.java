package com.techservices.usermanagement.models;

import lombok.Data;

@Data
public class CompanyDetails {

  private String companyName;
  private String address;
  private String city;
  private String state;
  private String postalCode;
  private String country;
  private String phoneNumber;
  private String website;

}
