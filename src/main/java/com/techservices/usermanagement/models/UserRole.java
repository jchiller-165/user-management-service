package com.techservices.usermanagement.models;

import java.util.Arrays;

import lombok.Getter;

@Getter
public enum UserRole {
  ADMIN("admin"),
  USER("user");

  private final String value;

  UserRole(final String value) {
    this.value = value;
  }

  public static UserRole fromString(final String incomingValue) {
    final UserRole roleType = findRoleType(incomingValue);
    if (roleType == null) {
      throw new IllegalArgumentException("Invalid role type: " + incomingValue);
    }
    return roleType;
  }

  public static UserRole findRoleType(final String incomingValue) {
    return Arrays.stream(UserRole.values()).filter(e -> e.value.equalsIgnoreCase(incomingValue)).findFirst()
        .orElse(null);
  }

}
