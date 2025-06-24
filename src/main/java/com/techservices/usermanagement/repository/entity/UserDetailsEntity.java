package com.techservices.usermanagement.repository.entity;

import com.techservices.usermanagement.models.UserRole;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.OffsetDateTime;

@Entity
@Data
public class UserDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String username;
    private String firstName;
    private String lastName;
    private UserRole.RoleTypeEnum userRole;
    private ContactInfoEntity contactInfo;
    private CompanyDetailsEntity companyDetails;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

}
