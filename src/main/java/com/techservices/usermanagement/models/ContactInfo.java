package com.techservices.usermanagement.models;

import lombok.Data;

@Data
public class ContactInfo {

    private String email;
    private String phoneNumber;
    private String address;
    private String city;
    private String state;
    private String postalCode;
    private String country;

}
