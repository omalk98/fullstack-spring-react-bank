package com.bank.backend.registration;

import com.bank.backend.registration.token.ConfirmationTokenService;
import com.bank.backend.registration.token.ConformationToken;
import com.bank.backend.userAccount.UserAccount;
import com.bank.backend.userAccount.UserAccountRole;
import com.bank.backend.userAccount.UserAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * Registers new user
 */
@AllArgsConstructor
@Service
public class RegistrationService {

    private final UserAccountService userAccountService;
    private final ConfirmationTokenService confirmationTokenService;
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

    @Transactional
    public String confirmToken(String token) {
        ConformationToken conformationToken = confirmationTokenService
                .getToken(token).orElseThrow(()->new IllegalStateException("Token not Found"));

        if(conformationToken.getConfirmedAt() != null)
            throw new IllegalStateException("Email already Confirmed");

        if(conformationToken.getExpiresAt().isBefore(LocalDateTime.now()))
            throw new IllegalStateException("Email already Confirmed");

        confirmationTokenService.setConfirmedAt(token);

        userAccountService.enableUserAccount(conformationToken.getUser().getEmail());

        return "confirmed";
    }
}
