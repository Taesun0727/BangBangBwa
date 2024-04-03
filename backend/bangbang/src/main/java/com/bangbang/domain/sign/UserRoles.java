package com.bangbang.domain.sign;

public enum UserRoles {
    ROLES_USER("ROLE_USER"),
    ROLES_BROKER("ROLE_BROKER"),
    ROLES_ADMIN("ROLE_ADMIN");

    private final String name;

    public String getName() {
        return name;
    }

    UserRoles(String name) {
        this.name = name;
    }
}
