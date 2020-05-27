package com.example.milk.domain;

import org.springframework.security.core.GrantedAuthority;

public enum UserRolesEnum implements GrantedAuthority {
    USER, ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }

}
