package com.techservices.usermanagement;

import com.techservices.usermanagement.models.CompanyDetails;
import com.techservices.usermanagement.models.ContactInfo;
import com.techservices.usermanagement.models.UserRole;
import com.techservices.usermanagement.models.reguests.UserRequest;

public class TestModelsCreator {

    public static UserRequest createUserRequest() {
        UserRequest request = new UserRequest();
        request.setUsername("someUsername");
        request.setEmail("someEmail@email.com");
        request.setRole(createUserRole());
        request.setCompanyDetails(createCompanyDetails());
        request.setContactInfo(createContactInfo());
        return request;
    }

    public static UserRole createUserRole() {
        UserRole userRole = new UserRole();
        userRole.setRoleType(UserRole.RoleTypeEnum.ADMIN);
        return userRole;
    }

    public static CompanyDetails createCompanyDetails() {
        CompanyDetails companyDetails = new CompanyDetails();
        companyDetails.setCompanyName("Tech Services Inc.");
        companyDetails.setAddress("123 Tech Lane");
        companyDetails.setCity("Tech City");
        companyDetails.setState("TS");
        companyDetails.setPostalCode("12345");
        companyDetails.setCountry("Techland");
        return companyDetails;
    }

    public static ContactInfo createContactInfo() {
        ContactInfo contactInfo = new ContactInfo();
        contactInfo.setPhoneNumber("123-456-7890");
        contactInfo.setAddress("456 Tech Street");
        contactInfo.setCity("Techville");
        contactInfo.setState("TS");
        contactInfo.setPostalCode("67890");
        contactInfo.setCountry("Techland");
        contactInfo.setFirstName("John");
        contactInfo.setLastName("Doe");
        return contactInfo;
    }
}
