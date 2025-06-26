package com.techservices.usermanagement;

import com.techservices.usermanagement.models.UserRole;
import com.techservices.usermanagement.models.requests.CreateUserRequest;
import com.techservices.usermanagement.models.requests.UpdateUserRequest;
import com.techservices.usermanagement.repository.entity.CompanyDetailsEntity;
import com.techservices.usermanagement.repository.entity.ContactInfoEntity;
import com.techservices.usermanagement.repository.entity.UserDetailsEntity;

public class TestModelsCreator {

  public static final String USERNAME = "testuser";
  public static final String EMAIL = "testEmail@email.com";
  public static final String PHONE_NUMBER = "1234567890";
  public static final String FIRST_NAME = "John";
  public static final String LAST_NAME = "Doe";
  public static final String ADDRESS = "123 Main St";
  public static final String CITY = "Anytown";
  public static final String STATE = "CA";
  public static final String POSTAL_CODE = "12345";
  public static final String COUNTRY = "USA";
  public static final String COMPANY_NAME = "Tech Services Inc.";
  public static final String COMPANY_ADDRESS = "123 Tech Lane";
  public static final String COMPANY_CITY = "Tech City";
  public static final String COMPANY_STATE = "TS";
  public static final String COMPANY_POSTAL_CODE = "12345";
  public static final String COMPANY_COUNTRY = "Techland";

  public static CreateUserRequest createUserCreateRequest() {
    CreateUserRequest request = new CreateUserRequest();
    request.setUsername(USERNAME);

    request.setUserRole(createUserRole());
    request.setCompanyDetails(createCompanyDetails());
    request.setContactInfo(createContactInfo());
    return request;
  }

  public static UpdateUserRequest createUpdateUserRequest() {
    UpdateUserRequest request = new UpdateUserRequest();
    request.setUsername(USERNAME);

    request.setUserRole(createUserRole());
    request.setCompanyDetails(createUpdateCompanyDetails());
    request.setContactInfo(createUpdateContactInfo());
    return request;
  }

  public static UserRole.RoleTypeEnum createUserRole() {
    UserRole userRole = new UserRole();
    userRole.setRoleType(UserRole.RoleTypeEnum.ADMIN);
    return userRole.getRoleType();
  }

  public static CreateUserRequest.CompanyDetails createCompanyDetails() {
    CreateUserRequest.CompanyDetails companyDetails = new CreateUserRequest.CompanyDetails();
    companyDetails.setCompanyName(COMPANY_NAME);
    companyDetails.setAddress(COMPANY_ADDRESS);
    companyDetails.setCity(COMPANY_CITY);
    companyDetails.setState(COMPANY_STATE);
    companyDetails.setPostalCode(COMPANY_POSTAL_CODE);
    companyDetails.setCountry(COMPANY_COUNTRY);
    return companyDetails;
  }

  public static CreateUserRequest.ContactInfo createContactInfo() {
    CreateUserRequest.ContactInfo contactInfo = new CreateUserRequest.ContactInfo();
    contactInfo.setFirstName(FIRST_NAME);
    contactInfo.setLastName(LAST_NAME);
    contactInfo.setPhoneNumber(PHONE_NUMBER);
    contactInfo.setAddress(ADDRESS);
    contactInfo.setCity(CITY);
    contactInfo.setState(STATE);
    contactInfo.setPostalCode(POSTAL_CODE);
    contactInfo.setCountry(COUNTRY);
    contactInfo.setEmail(EMAIL);
    return contactInfo;
  }

  public static UpdateUserRequest.CompanyDetails createUpdateCompanyDetails() {
    UpdateUserRequest.CompanyDetails companyDetails = new UpdateUserRequest.CompanyDetails();
    companyDetails.setCompanyName(COMPANY_NAME);
    companyDetails.setAddress(COMPANY_ADDRESS);
    companyDetails.setCity(COMPANY_CITY);
    companyDetails.setState(COMPANY_STATE);
    companyDetails.setPostalCode(COMPANY_POSTAL_CODE);
    companyDetails.setCountry(COMPANY_COUNTRY);
    return companyDetails;
  }

  public static UpdateUserRequest.ContactInfo createUpdateContactInfo() {
    UpdateUserRequest.ContactInfo contactInfo = new UpdateUserRequest.ContactInfo();
    contactInfo.setFirstName(FIRST_NAME);
    contactInfo.setLastName(LAST_NAME);
    contactInfo.setPhoneNumber(PHONE_NUMBER);
    contactInfo.setAddress(ADDRESS);
    contactInfo.setCity(CITY);
    contactInfo.setState(STATE);
    contactInfo.setPostalCode(POSTAL_CODE);
    contactInfo.setCountry(COUNTRY);
    contactInfo.setEmail(EMAIL);
    return contactInfo;
  }

  public static UserDetailsEntity createUserDetailsEntity() {
    UserDetailsEntity userDetailsEntity = new UserDetailsEntity();
    userDetailsEntity.setUsername(USERNAME);
    userDetailsEntity.setUserRole(createUserRole());
    userDetailsEntity.setCompanyDetails(createCompanyDetailsEntity());
    userDetailsEntity.setContactInfo(createContactInfoEntity());
    return userDetailsEntity;
  }

  public static CompanyDetailsEntity createCompanyDetailsEntity() {
    CompanyDetailsEntity companyDetailsEntity = new CompanyDetailsEntity();
    companyDetailsEntity.setCompanyName(COMPANY_NAME);
    companyDetailsEntity.setAddress(COMPANY_ADDRESS);
    companyDetailsEntity.setCity(COMPANY_CITY);
    companyDetailsEntity.setState(COMPANY_STATE);
    companyDetailsEntity.setPostalCode(COMPANY_POSTAL_CODE);
    companyDetailsEntity.setCountry(COMPANY_COUNTRY);
    return companyDetailsEntity;
  }

  public static ContactInfoEntity createContactInfoEntity() {
    ContactInfoEntity contactInfoEntity = new ContactInfoEntity();
    contactInfoEntity.setEmail(EMAIL);
    contactInfoEntity.setPhoneNumber(PHONE_NUMBER);
    contactInfoEntity.setAddress(ADDRESS);
    contactInfoEntity.setCity(CITY);
    contactInfoEntity.setState(STATE);
    contactInfoEntity.setPostalCode(POSTAL_CODE);
    contactInfoEntity.setCountry(COUNTRY);
    return contactInfoEntity;
  }

}
