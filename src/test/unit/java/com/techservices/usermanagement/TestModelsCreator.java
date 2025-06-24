package com.techservices.usermanagement;

import com.techservices.usermanagement.models.requests.CreateUserRequest;
import com.techservices.usermanagement.models.requests.UpdateUserRequest;
import com.techservices.usermanagement.models.UserRole;

public class TestModelsCreator {

    public static CreateUserRequest createUserCreateRequest() {
        CreateUserRequest request = new CreateUserRequest();
        request.setUsername("someUsername");
        request.setFirstName("firstName");
        request.setLastName("lastName");
        request.setUserRole(createUserRole());
        request.setCompanyDetails(createCompanyDetails());
        request.setContactInfo(createContactInfo());
        return request;
    }

    public static UpdateUserRequest createUpdateUserRequest() {
        UpdateUserRequest request = new UpdateUserRequest();
        request.setUsername("someUsername");
        request.setFirstName("firstName");
        request.setLastName("lastName");
        request.setUserRole(createUserRole());
        request.setCompanyDetails(createUpdateCompanyDetails());
        request.setContactInfo(createUpdateContactInfo());
        return request;
    }

    public static UserRole createUserRole() {
        UserRole userRole = new UserRole();
        userRole.setRoleType(UserRole.RoleTypeEnum.ADMIN);
        return userRole;
    }

    public static CreateUserRequest.CompanyDetails createCompanyDetails() {
        CreateUserRequest.CompanyDetails companyDetails = new CreateUserRequest.CompanyDetails();
        companyDetails.setCompanyName("Tech Services Inc.");
        companyDetails.setCompanyAddress("123 Tech Lane");
        companyDetails.setCompanyCity("Tech City");
        companyDetails.setCompanyState("TS");
        companyDetails.setCompanyPostalCode("12345");
        companyDetails.setCompanyCountry("Techland");
        return companyDetails;
    }

    public static CreateUserRequest.ContactInfo createContactInfo() {
        CreateUserRequest.ContactInfo contactInfo = new CreateUserRequest.ContactInfo();
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
        companyDetails.setCompanyAddress("123 Tech Lane");
        companyDetails.setCompanyCity("Tech City");
        companyDetails.setCompanyState("TS");
        companyDetails.setCompanyPostalCode("12345");
        companyDetails.setCompanyCountry("Techland");
        return companyDetails;
    }

    public static UpdateUserRequest.ContactInfo createUpdateContactInfo() {
        UpdateUserRequest.ContactInfo contactInfo = new UpdateUserRequest.ContactInfo();
        contactInfo.setPhoneNumber("123-456-7890");
        contactInfo.setAddress("456 Tech Street");
        contactInfo.setCity("Techville");
        contactInfo.setState("TS");
        contactInfo.setPostalCode("67890");
        contactInfo.setCountry("Techland");
        contactInfo.setEmail("someEmail@email.com");
        return contactInfo;
    }

}
