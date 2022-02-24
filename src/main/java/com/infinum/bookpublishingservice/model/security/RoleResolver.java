package com.infinum.bookpublishingservice.model.security;

import com.infinum.bookpublishingservice.model.security.Role;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RoleResolver {

    public static Role resolveRole(String role) {

        switch (role) {
            case "admin":
                return Role.ADMIN;
            case "author":
                return Role.AUTHOR;
            default:
                return Role.PUBLIC;
        }
    }

}