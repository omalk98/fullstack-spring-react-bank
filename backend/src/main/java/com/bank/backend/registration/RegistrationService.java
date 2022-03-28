package com.bank.backend.registration;

import com.bank.backend.userAccount.UserAccount;
import com.bank.backend.userAccount.UserAccountRole;
import com.bank.backend.userAccount.UserAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Registers new user
 */
@AllArgsConstructor
@Service
public class RegistrationService {

    private final UserAccountService userAccountService;
    private final EmailValidator emailValidator;

    public String register(RegistrationRequest request) {
         if (!emailValidator.test(request.getEmail()))
             throw new IllegalStateException("Invalid Email");

        return userAccountService.signupNewUser(
                new UserAccount(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getUsername(),
                        request.getPassword(),
                        request.getDateOfBirth(),
                        UserAccountRole.USER
                )
        );
    }
}
