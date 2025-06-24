package com.techservices.usermanagement.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.OffsetDateTime;

@Entity
@Data
public class CompanyDetailsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String companyName;
    private String companyAddress;
    private String companyCity;
    private String companyState;
    private String companyPostalCode;
    private String companyCountry;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;

}
