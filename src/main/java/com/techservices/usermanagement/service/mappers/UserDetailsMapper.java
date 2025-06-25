package com.techservices.usermanagement.service.mappers;

import com.techservices.usermanagement.models.CompanyDetails;
import com.techservices.usermanagement.models.ContactInfo;
import com.techservices.usermanagement.models.UserDetails;
import com.techservices.usermanagement.models.UserRole;
import com.techservices.usermanagement.models.requests.CreateUserRequest;
import com.techservices.usermanagement.repository.entity.CompanyDetailsEntity;
import com.techservices.usermanagement.repository.entity.ContactInfoEntity;
import com.techservices.usermanagement.repository.entity.UserDetailsEntity;
import lombok.NonNull;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserRole.class})
public abstract class UserDetailsMapper {

  @Mapping(source = "id", target = "userId")
  @Mapping(source = "username", target = "username")
  @Mapping(source = "userRole", target = "userRole")
  @Mapping(expression = "java(toContactInfo(userDetailsEntity.getContactInfo()))", target = "contactInfo")
  @Mapping(expression = "java(toCompanyDetails(userDetailsEntity.getCompanyDetails()))", target = "companyDetails")
  public abstract UserDetails toUserDetails(UserDetailsEntity userDetailsEntity);

  @Mapping(source = "email", target = "email")
  @Mapping(source = "phoneNumber", target = "phoneNumber")
  @Mapping(source = "firstName", target = "firstName")
  @Mapping(source = "lastName", target = "lastName")
  @Mapping(source = "address", target = "address")
  @Mapping(source = "city", target = "city")
  @Mapping(source = "state", target = "state")
  @Mapping(source = "postalCode", target = "postalCode")
  @Mapping(source = "country", target = "country")
  public abstract ContactInfo toContactInfo(ContactInfoEntity contactInfoEntity);

  @Mapping(source = "companyName", target = "companyName")
  @Mapping(source = "address", target = "address")
  @Mapping(source = "city", target = "city")
  @Mapping(source = "state", target = "state")
  @Mapping(source = "postalCode", target = "postalCode")
  @Mapping(source = "country", target = "country")
  public abstract CompanyDetails toCompanyDetails(CompanyDetailsEntity companyDetailsEntity);

  @Mapping(source = "username", target = "username")
  @Mapping(expression = "java(getRoleType(request.getUserRole()))", target = "userRole")
  @Mapping(expression = "java(toContactInfoEntity(request.getContactInfo()))", target = "contactInfo")
  @Mapping(expression = "java(toCompanyDetailsEntity(request.getCompanyDetails()))", target = "companyDetails")
  public abstract UserDetailsEntity toUserDetailsEntity(@NonNull CreateUserRequest request);

  @Mapping(source = "email", target = "email")
  @Mapping(source = "phoneNumber", target = "phoneNumber")
  @Mapping(source = "firstName", target = "firstName")
  @Mapping(source = "lastName", target = "lastName")
  @Mapping(source = "address", target = "address")
  @Mapping(source = "city", target = "city")
  @Mapping(source = "state", target = "state")
  @Mapping(source = "postalCode", target = "postalCode")
  @Mapping(source = "country", target = "country")
  public abstract ContactInfoEntity toContactInfoEntity(CreateUserRequest.ContactInfo contactInfo);

  @Mapping(source = "companyName", target = "companyName")
  @Mapping(source = "address", target = "address")
  @Mapping(source = "city", target = "city")
  @Mapping(source = "state", target = "state")
  @Mapping(source = "postalCode", target = "postalCode")
  @Mapping(source = "country", target = "country")
  public abstract CompanyDetailsEntity toCompanyDetailsEntity(CreateUserRequest.CompanyDetails companyDetails);

  protected UserRole.RoleTypeEnum getRoleType(UserRole.RoleTypeEnum userRole) {
    return userRole != null ? UserRole.RoleTypeEnum.findRoleType(userRole.getValue()) : null;
  }

}
