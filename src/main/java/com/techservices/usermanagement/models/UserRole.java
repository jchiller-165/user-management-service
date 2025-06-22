package com.techservices.usermanagement.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Arrays;

@Data
public class UserRole {

    @NotNull
    private UserRole.RoleTypeEnum roleType;

    public enum RoleTypeEnum {
        ADMIN("admin"),
        OWNER("owner"),
        TECHNICIAN("technician"),
        CUSTOMER("customer");

        private final String value;

        RoleTypeEnum(final String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
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
