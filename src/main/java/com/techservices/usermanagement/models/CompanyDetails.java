package com.techservices.usermanagement.models;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CompanyDetails {

    @NotBlank
    private String companyName;

    @NotBlank
    private String address;

    @NotBlank
    private String city;

    @NotBlank
    private String state;

    @NotBlank
    private String postalCode;

    @NotBlank
    private String country;

}
