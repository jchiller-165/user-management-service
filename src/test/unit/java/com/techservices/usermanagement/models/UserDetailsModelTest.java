package com.techservices.usermanagement.models;

import static com.techservices.usermanagement.TestModelsCreator.ADDRESS;
import static com.techservices.usermanagement.TestModelsCreator.CITY;
import static com.techservices.usermanagement.TestModelsCreator.COMPANY_ADDRESS;
import static com.techservices.usermanagement.TestModelsCreator.COMPANY_CITY;
import static com.techservices.usermanagement.TestModelsCreator.COMPANY_COUNTRY;
import static com.techservices.usermanagement.TestModelsCreator.COMPANY_NAME;
import static com.techservices.usermanagement.TestModelsCreator.COMPANY_POSTAL_CODE;
import static com.techservices.usermanagement.TestModelsCreator.COMPANY_STATE;
import static com.techservices.usermanagement.TestModelsCreator.COUNTRY;
import static com.techservices.usermanagement.TestModelsCreator.EMAIL;
import static com.techservices.usermanagement.TestModelsCreator.FIRST_NAME;
import static com.techservices.usermanagement.TestModelsCreator.LAST_NAME;
import static com.techservices.usermanagement.TestModelsCreator.PHONE_NUMBER;
import static com.techservices.usermanagement.TestModelsCreator.POSTAL_CODE;
import static com.techservices.usermanagement.TestModelsCreator.STATE;
import static com.techservices.usermanagement.TestModelsCreator.USERNAME;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class UserDetailsModelTest {

  @Test
  void testUserDetailsModel() {
    UserDetails userDetails = new UserDetails();
    userDetails.setUsername(USERNAME);
    userDetails.setUserRole(UserRole.RoleTypeEnum.ADMIN);

    assertEquals(USERNAME, userDetails.getUsername());
    assertEquals(UserRole.RoleTypeEnum.ADMIN, userDetails.getUserRole());
  }

  @Test
  void testContactInfoModel() {
    ContactInfo contactInfo = new ContactInfo();
    contactInfo.setEmail(EMAIL);
    contactInfo.setPhoneNumber(PHONE_NUMBER);
    contactInfo.setFirstName(FIRST_NAME);
    contactInfo.setLastName(LAST_NAME);
    contactInfo.setAddress(ADDRESS);
    contactInfo.setCity(CITY);
    contactInfo.setState(STATE);
    contactInfo.setPostalCode(POSTAL_CODE);
    contactInfo.setCountry(COUNTRY);

    assertEquals(EMAIL, contactInfo.getEmail());
    assertEquals(PHONE_NUMBER, contactInfo.getPhoneNumber());
    assertEquals(FIRST_NAME, contactInfo.getFirstName());
    assertEquals(LAST_NAME, contactInfo.getLastName());
    assertEquals(ADDRESS, contactInfo.getAddress());
    assertEquals(CITY, contactInfo.getCity());
    assertEquals(STATE, contactInfo.getState());
    assertEquals(POSTAL_CODE, contactInfo.getPostalCode());
    assertEquals(COUNTRY, contactInfo.getCountry());
  }

  @Test
  void testCompanyDetailsModel() {
    CompanyDetails companyDetails = new CompanyDetails();
    companyDetails.setCompanyName(COMPANY_NAME);
    companyDetails.setAddress(COMPANY_ADDRESS);
    companyDetails.setCity(COMPANY_CITY);
    companyDetails.setState(COMPANY_STATE);
    companyDetails.setPostalCode(COMPANY_POSTAL_CODE);
    companyDetails.setCountry(COMPANY_COUNTRY);

    assertEquals(COMPANY_NAME, companyDetails.getCompanyName());
    assertEquals(COMPANY_ADDRESS, companyDetails.getAddress());
    assertEquals(COMPANY_CITY, companyDetails.getCity());
    assertEquals(COMPANY_STATE, companyDetails.getState());
    assertEquals(COMPANY_POSTAL_CODE, companyDetails.getPostalCode());
    assertEquals(COMPANY_COUNTRY, companyDetails.getCountry());
  }
}
