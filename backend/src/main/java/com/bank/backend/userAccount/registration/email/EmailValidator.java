package com.bank.backend.userAccount.registration.email;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;

/**
 * Validates email
 */
@Service
public class EmailValidator implements Predicate<String> {

    @Override
    public boolean test(String s) {
        return s.matches("(.+)@(.+)\\.(.+)");
    }
}
