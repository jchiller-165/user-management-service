package com.techservices.usermanagement;

import com.techservices.usermanagement.models.UserRole;
import com.techservices.usermanagement.models.requests.CreateUserRequest;
import com.techservices.usermanagement.models.requests.UpdateUserRequest;
import com.techservices.usermanagement.repository.entity.CompanyDetailsEntity;
import com.techservices.usermanagement.repository.entity.ContactInfoEntity;
import com.techservices.usermanagement.repository.entity.UserDetailsEntity;

public class TestModelsCreator {

  public static CreateUserRequest createUserCreateRequest() {
    CreateUserRequest request = new CreateUserRequest();
    request.setUsername("someUsername");

    request.setUserRole(createUserRole());
    request.setCompanyDetails(createCompanyDetails());
    request.setContactInfo(createContactInfo());
    return request;
  }

  public static UpdateUserRequest createUpdateUserRequest() {
    UpdateUserRequest request = new UpdateUserRequest();
    request.setUsername("someUsername");

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
    companyDetails.setCompanyName("Tech Services Inc.");
    companyDetails.setAddress("123 Tech Lane");
    companyDetails.setCity("Tech City");
    companyDetails.setState("TS");
    companyDetails.setPostalCode("12345");
    companyDetails.setCountry("Techland");
    return companyDetails;
  }

  public static CreateUserRequest.ContactInfo createContactInfo() {
    CreateUserRequest.ContactInfo contactInfo = new CreateUserRequest.ContactInfo();
    contactInfo.setFirstName("firstName");
    contactInfo.setLastName("lastName");
    contactInfo.setPhoneNumber("123-456-7890");
    contactInfo.setAddress("456 Tech Street");
    contactInfo.setCity("Techville");
    contactInfo.setState("TS");
    contactInfo.setPostalCode("67890");
    contactInfo.setCountry("Techland");
    contactInfo.setEmail("someEmail@email.com");
    return contactInfo;
  }

  public static UpdateUserRequest.CompanyDetails createUpdateCompanyDetails() {
    UpdateUserRequest.CompanyDetails companyDetails = new UpdateUserRequest.CompanyDetails();
    companyDetails.setCompanyName("Tech Services Inc.");
    companyDetails.setAddress("123 Tech Lane");
    companyDetails.setCity("Tech City");
    companyDetails.setState("TS");
    companyDetails.setPostalCode("12345");
    companyDetails.setCountry("Techland");
    return companyDetails;
  }

  public static UpdateUserRequest.ContactInfo createUpdateContactInfo() {
    UpdateUserRequest.ContactInfo contactInfo = new UpdateUserRequest.ContactInfo();
    contactInfo.setFirstName("firstName");
    contactInfo.setLastName("lastName");
    contactInfo.setPhoneNumber("123-456-7890");
    contactInfo.setAddress("456 Tech Street");
    contactInfo.setCity("Techville");
    contactInfo.setState("TS");
    contactInfo.setPostalCode("67890");
    contactInfo.setCountry("Techland");
    contactInfo.setEmail("someEmail@email.com");
    return contactInfo;
  }

  public static UserDetailsEntity createUserDetailsEntity() {
    UserDetailsEntity userDetailsEntity = new UserDetailsEntity();
    userDetailsEntity.setUsername("someUsername");
    userDetailsEntity.setUserRole(createUserRole());
    userDetailsEntity.setCompanyDetails(createCompanyDetailsEntity());
    userDetailsEntity.setContactInfo(createContactInfoEntity());
    return userDetailsEntity;
  }

  public static CompanyDetailsEntity createCompanyDetailsEntity() {
    CompanyDetailsEntity companyDetailsEntity = new CompanyDetailsEntity();
    companyDetailsEntity.setCompanyName("Tech Services Inc.");
    companyDetailsEntity.setAddress("123 Tech Lane");
    companyDetailsEntity.setCity("Tech City");
    companyDetailsEntity.setState("TS");
    companyDetailsEntity.setPostalCode("12345");
    companyDetailsEntity.setCountry("Techland");
    return companyDetailsEntity;
  }

  public static ContactInfoEntity createContactInfoEntity() {
    ContactInfoEntity contactInfoEntity = new ContactInfoEntity();
    contactInfoEntity.setEmail("test@email.com");
    contactInfoEntity.setPhoneNumber("123-456-7890");
    contactInfoEntity.setAddress("456 Tech Street");
    contactInfoEntity.setCity("Techville");
    contactInfoEntity.setState("TS");
    contactInfoEntity.setPostalCode("67890");
    contactInfoEntity.setCountry("Techland");
    return contactInfoEntity;
  }

}
