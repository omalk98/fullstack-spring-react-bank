package com.bank.backend.security.access;

import java.util.HashSet;
import java.util.Set;

import static com.bank.backend.security.access.UserAccountPermission.*;
/**
 * Enum mentioning roles of the user
 */
public enum UserAccountRole {
    ADMIN(new HashSet<>(Set.of(USER_ACCOUNT_READ, USER_ACCOUNT_WRITE, TRANSACTION_READ, TRANSACTION_WRITE, BANK_ACCOUNT_READ, BANK_ACCOUNT_WRITE))),
    TELLER(new HashSet<>()),
    USER(new HashSet<>(Set.of(USER_ACCOUNT_READ, USER_ACCOUNT_WRITE, TRANSACTION_READ, TRANSACTION_WRITE, BANK_ACCOUNT_READ, BANK_ACCOUNT_WRITE)));

    private final Set<UserAccountPermission> permissions;

    UserAccountRole(Set<UserAccountPermission> permissions) {
        this.permissions = permissions;
    }
}
