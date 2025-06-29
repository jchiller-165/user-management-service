package com.techservices.usermanagement.repository.entity;

import java.time.OffsetDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "company_info", schema = "user_management")
public class CompanyDetailsEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "company_name", nullable = false, unique = true)
  private String companyName;

  @Column(name = "address")
  private String address;

  @Column(name = "city")
  private String city;

  @Column(name = "state")
  private String state;

  @Column(name = "country")
  private String country;

  @Column(name = "postal_code")
  private String postalCode;

  @Column(name = "phone_number")
  private String phoneNumber;

  @Column(name = "website")
  private String website;

  @Column(name = "created_at", updatable = false)
  private OffsetDateTime createdAt;

  @Column(name = "updated_at")
  private OffsetDateTime updatedAt;

}