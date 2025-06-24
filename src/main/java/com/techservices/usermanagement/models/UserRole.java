package com.techservices.usermanagement.models;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;

import java.util.Arrays;

@Data
public class UserRole {

    @NotNull
    private UserRole.RoleTypeEnum roleType;

    @Getter
    public enum RoleTypeEnum {
        ADMIN("admin"),
        USER("user");

        private final String value;

        RoleTypeEnum(final String value) {
            this.value = value;
        }

        public static RoleTypeEnum fromString(final String incomingValue) {
            final RoleTypeEnum roleType = findRoleType(incomingValue);
            if (roleType == null) {
                throw new IllegalArgumentException("Invalid role type: " + incomingValue);
            }
            return roleType;
        }

        public static RoleTypeEnum findRoleType(final String incomingValue) {
            return Arrays.stream(RoleTypeEnum.values())
                    .filter(e -> e.value.equalsIgnoreCase(incomingValue))
                    .findFirst()
                    .orElse(null);
        }
    }

}
