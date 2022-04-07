package com.bank.backend.registration;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * Routes for user registration
 */
@RestController
@RequestMapping(path = "api/user/registration")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping
    public String register(@RequestBody RegistrationRequest request) {
        return registrationService.register(request);
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {
        return registrationService.confirmToken(token);
    }
}
