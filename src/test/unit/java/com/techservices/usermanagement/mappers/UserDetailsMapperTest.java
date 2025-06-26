package com.techservices.usermanagement.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import com.techservices.usermanagement.TestModelsCreator;
import com.techservices.usermanagement.models.CompanyDetails;
import com.techservices.usermanagement.models.ContactInfo;
import com.techservices.usermanagement.models.UserDetails;
import com.techservices.usermanagement.models.UserRole;
import com.techservices.usermanagement.models.requests.CreateUserRequest;
import com.techservices.usermanagement.models.requests.UpdateUserRequest;
import com.techservices.usermanagement.repository.entity.CompanyDetailsEntity;
import com.techservices.usermanagement.repository.entity.ContactInfoEntity;
import com.techservices.usermanagement.repository.entity.UserDetailsEntity;
import com.techservices.usermanagement.service.mappers.UserDetailsMapper;

class UserDetailsMapperTest {

  private final UserDetailsMapper mapper = Mappers.getMapper(UserDetailsMapper.class);

  @Test
  void toUserDetails_mapsFieldsCorrectly() {
    UserDetailsEntity expectedEntity = TestModelsCreator.createUserDetailsEntity();
    UserDetails userDetails = mapper.toUserDetails(expectedEntity);

    assertNotNull(userDetails);
    assertEquals(expectedEntity.getId(), userDetails.getUserId());
    assertEquals(expectedEntity.getUsername(), userDetails.getUsername());
    assertEquals(expectedEntity.getUserRole(), userDetails.getUserRole());
    assertTrue(compareCompanyDetails(expectedEntity.getCompanyDetails(), userDetails.getCompanyDetails()));
    assertTrue(compareContactInfo(expectedEntity.getContactInfo(), userDetails.getContactInfo()));
  }

  @Test
  void toUserDetailsEntity_mapsFieldsCorrectly() {
    CreateUserRequest request = TestModelsCreator.createUserCreateRequest();
    UserDetailsEntity entity = mapper.toUserDetailsEntity(request);

    assertNotNull(entity);
    assertEquals(request.getUsername(), entity.getUsername());
    assertEquals(UserRole.RoleTypeEnum.findRoleType(request.getUserRole().getValue()), entity.getUserRole());
    assertTrue(compareCompanyDetailsEntity(request.getCompanyDetails(), entity.getCompanyDetails()));
    assertTrue(compareContactInfoEntity(request.getContactInfo(), entity.getContactInfo()));
  }

  @Test
  void updateUserDetailsEntity_updatesFieldsCorrectly() {
    UserDetailsEntity entity = TestModelsCreator.createUserDetailsEntity();
    UpdateUserRequest request = TestModelsCreator.createUpdateUserRequest();

    // Change initial values to ensure update is effective
    entity.setUsername("oldUsername");
    entity.setUserRole(null);
    entity.setContactInfo(null);
    entity.setCompanyDetails(null);

    mapper.updateUserDetailsEntity(entity, request);

    assertEquals(request.getUsername(), entity.getUsername());
    assertEquals(UserRole.RoleTypeEnum.findRoleType(request.getUserRole().getValue()), entity.getUserRole());

    // ContactInfo assertions
    assertNotNull(entity.getContactInfo());
    assertEquals(request.getContactInfo().getEmail(), entity.getContactInfo().getEmail());
    assertEquals(request.getContactInfo().getPhoneNumber(), entity.getContactInfo().getPhoneNumber());
    assertEquals(request.getContactInfo().getFirstName(), entity.getContactInfo().getFirstName());
    assertEquals(request.getContactInfo().getLastName(), entity.getContactInfo().getLastName());
    assertEquals(request.getContactInfo().getAddress(), entity.getContactInfo().getAddress());
    assertEquals(request.getContactInfo().getCity(), entity.getContactInfo().getCity());
    assertEquals(request.getContactInfo().getState(), entity.getContactInfo().getState());
    assertEquals(request.getContactInfo().getPostalCode(), entity.getContactInfo().getPostalCode());
    assertEquals(request.getContactInfo().getCountry(), entity.getContactInfo().getCountry());

    // CompanyDetails assertions
    assertNotNull(entity.getCompanyDetails());
    assertEquals(request.getCompanyDetails().getCompanyName(), entity.getCompanyDetails().getCompanyName());
    assertEquals(request.getCompanyDetails().getAddress(), entity.getCompanyDetails().getAddress());
    assertEquals(request.getCompanyDetails().getCity(), entity.getCompanyDetails().getCity());
    assertEquals(request.getCompanyDetails().getState(), entity.getCompanyDetails().getState());
    assertEquals(request.getCompanyDetails().getPostalCode(), entity.getCompanyDetails().getPostalCode());
    assertEquals(request.getCompanyDetails().getCountry(), entity.getCompanyDetails().getCountry());
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

  private boolean compareCompanyDetailsEntity(CreateUserRequest.CompanyDetails expected, CompanyDetailsEntity actual) {
    return expected.getCompanyName().equals(actual.getCompanyName()) && expected.getAddress()
        .equals(actual.getAddress()) && expected.getCity().equals(actual.getCity()) && expected.getState()
        .equals(actual.getState()) && expected.getPostalCode().equals(actual.getPostalCode()) && expected.getCountry()
        .equals(actual.getCountry());
  }

  private boolean compareContactInfoEntity(CreateUserRequest.ContactInfo expected, ContactInfoEntity actual) {
    return expected.getEmail().equals(actual.getEmail()) && expected.getPhoneNumber().equals(actual.getPhoneNumber())
        && expected.getCity().equals(actual.getCity()) && expected.getState().equals(actual.getState())
        && expected.getPostalCode().equals(actual.getPostalCode()) && expected.getCountry().equals(actual.getCountry());
  }
}