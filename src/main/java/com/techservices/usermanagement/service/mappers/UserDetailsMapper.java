package com.techservices.usermanagement.service.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.techservices.usermanagement.models.CompanyDetails;
import com.techservices.usermanagement.models.ContactInfo;
import com.techservices.usermanagement.models.UserDetails;
import com.techservices.usermanagement.repository.entity.CompanyDetailsEntity;
import com.techservices.usermanagement.repository.entity.ContactInfoEntity;
import com.techservices.usermanagement.repository.entity.UserDetailsEntity;

@Mapper
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

}
