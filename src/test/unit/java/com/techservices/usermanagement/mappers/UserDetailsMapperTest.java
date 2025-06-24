package com.techservices.usermanagement.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.OffsetDateTime;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import com.techservices.usermanagement.models.CompanyDetails;
import com.techservices.usermanagement.models.ContactInfo;
import com.techservices.usermanagement.models.UserDetails;
import com.techservices.usermanagement.models.UserRole;
import com.techservices.usermanagement.repository.entity.CompanyDetailsEntity;
import com.techservices.usermanagement.repository.entity.ContactInfoEntity;
import com.techservices.usermanagement.repository.entity.UserDetailsEntity;
import com.techservices.usermanagement.service.mappers.UserDetailsMapper;

public class UserDetailsMapperTest {

  private final UserDetailsMapper mapper = Mappers.getMapper(UserDetailsMapper.class);

  @Test
  void toUserDetails_mapsFieldsCorrectly() {
    UserDetailsEntity expectedEntity = createUserDetailsEntity();
    UserDetails userDetails = mapper.toUserDetails(expectedEntity);

    assertNotNull(userDetails);

    assertEquals(expectedEntity.getId(), userDetails.getUserId());
    assertEquals(expectedEntity.getUsername(), userDetails.getUsername());
    assertEquals(expectedEntity.getUserRole(), userDetails.getUserRole());
    assertTrue(compareCompanyDetails(expectedEntity.getCompanyDetails(), userDetails.getCompanyDetails()));
    assertTrue(compareContactInfo(expectedEntity.getContactInfo(), userDetails.getContactInfo()));
  }

  private UserDetailsEntity createUserDetailsEntity() {
    UserDetailsEntity entity = new UserDetailsEntity();
    entity.setId(1L);
    entity.setUsername("testuser");
    entity.setUserRole(UserRole.RoleTypeEnum.ADMIN);

    entity.setCompanyDetails(createCompanyDetailsEntity());
    entity.setContactInfo(createContactInfoEntity());
    return entity;
  }

  private CompanyDetailsEntity createCompanyDetailsEntity() {
    CompanyDetailsEntity companyDetails = new CompanyDetailsEntity();
    companyDetails.setId(1L);
    companyDetails.setCompanyName("Tech Services");
    companyDetails.setAddress("123 Tech Lane");
    companyDetails.setCity("Tech City");
    companyDetails.setState("Tech State");
    companyDetails.setPostalCode("12345");
    companyDetails.setCountry("Techland");
    companyDetails.setCreatedAt(OffsetDateTime.now());
    companyDetails.setUpdatedAt(OffsetDateTime.now());
    return companyDetails;
  }

  private ContactInfoEntity createContactInfoEntity() {
    ContactInfoEntity contactInfo = new ContactInfoEntity();
    contactInfo.setFirstName("John");
    contactInfo.setLastName("Doe");
    contactInfo.setEmail("someEmail@email.com");
    contactInfo.setPhoneNumber("123-456-7890");
    contactInfo.setId(1L);
    contactInfo.setCity("Tech City");
    contactInfo.setState("Tech State");
    contactInfo.setPostalCode("12345");
    contactInfo.setCountry("Techland");
    contactInfo.setCreatedAt(OffsetDateTime.now());
    contactInfo.setUpdatedAt(OffsetDateTime.now());
    return contactInfo;
  }

  private boolean compareCompanyDetails(CompanyDetailsEntity expected, CompanyDetails companyDetails) {
    return expected.getCompanyName().equals(companyDetails.getCompanyName()) && expected.getAddress()
        .equals(companyDetails.getAddress()) && expected.getCity().equals(companyDetails.getCity())
        && expected.getState().equals(companyDetails.getState()) && expected.getPostalCode()
        .equals(companyDetails.getPostalCode()) && expected.getCountry().equals(companyDetails.getCountry());
  }

  private boolean compareContactInfo(ContactInfoEntity expected, ContactInfo contactInfo) {
    return expected.getEmail().equals(contactInfo.getEmail()) && expected.getPhoneNumber()
        .equals(contactInfo.getPhoneNumber()) && expected.getCity().equals(contactInfo.getCity()) && expected.getState()
        .equals(contactInfo.getState()) && expected.getPostalCode().equals(contactInfo.getPostalCode())
        && expected.getCountry().equals(contactInfo.getCountry());
  }

}
