package com.techservices.usermanagement.service.mappers;

import com.techservices.usermanagement.models.CompanyDetails;
import com.techservices.usermanagement.models.ContactInfo;
import com.techservices.usermanagement.models.UserDetails;
import com.techservices.usermanagement.repository.entity.CompanyDetailsEntity;
import com.techservices.usermanagement.repository.entity.ContactInfoEntity;
import com.techservices.usermanagement.repository.entity.UserDetailsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public abstract class UserDetailsMapper {

    @Mapping(source = "userId", target = "userId")
    @Mapping(source = "username", target = "username")
    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "userRole", target = "userRole")
    @Mapping(expression = "java(toContactInfo(userDetailsEntity.getContactInfo()))", target = "contactInfo")
    @Mapping(expression = "java(toCompanyDetails(userDetailsEntity.getCompanyDetails()))", target = "companyDetails")
    public abstract UserDetails toUserDetails(UserDetailsEntity userDetailsEntity);

    @Mapping(source = "email", target = "email")
    @Mapping(source = "phoneNumber", target = "phoneNumber")
    @Mapping(source = "address", target = "address")
    @Mapping(source = "city", target = "city")
    @Mapping(source = "state", target = "state")
    @Mapping(source = "postalCode", target = "postalCode")
    @Mapping(source = "country", target = "country")
    public abstract ContactInfo toContactInfo(ContactInfoEntity contactInfoEntity);

    @Mapping(source = "companyName", target = "companyName")
    @Mapping(source = "companyAddress", target = "companyAddress")
    @Mapping(source = "companyCity", target = "companyCity")
    @Mapping(source = "companyState", target = "companyState")
    @Mapping(source = "companyPostalCode", target = "companyPostalCode")
    @Mapping(source = "companyCountry", target = "companyCountry")
    public abstract CompanyDetails toCompanyDetails(CompanyDetailsEntity companyDetailsEntity);

}
