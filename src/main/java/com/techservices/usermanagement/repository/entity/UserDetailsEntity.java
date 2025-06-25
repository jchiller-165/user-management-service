package com.techservices.usermanagement.repository.entity;

import com.techservices.usermanagement.models.UserRole;
import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

@Entity
@Data
@Table(name = "user_info", schema = "user_management")
public class UserDetailsEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "username", nullable = false)
  private String username;

  @Column(name = "role")
  @Enumerated(EnumType.STRING)
  private UserRole.RoleTypeEnum userRole;

  @ManyToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "company_id", nullable = false)
  private CompanyDetailsEntity companyDetails;

  @ManyToOne(cascade = CascadeType.PERSIST)
  @JoinColumn(name = "contact_id", nullable = false)
  private ContactInfoEntity contactInfo;

  @Column(name = "created_at", updatable = false)
  private OffsetDateTime createdAt;

  @Column(name = "updated_at")
  private OffsetDateTime updatedAt;

}
