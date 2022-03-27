package com.bank.backend.registration;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * User Registration Model
 */
@Getter
@Setter
public class RegistrationRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private LocalDate dateOfBirth;
}
