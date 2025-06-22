package com.techservices.usermanagement.models;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ContactInfo {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String phoneNumber;

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
