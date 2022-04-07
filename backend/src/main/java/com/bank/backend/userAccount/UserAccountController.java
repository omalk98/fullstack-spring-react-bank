package com.bank.backend.userAccount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for user account
 */
@RestController
@RequestMapping(path="api")
@CrossOrigin("*")
public class UserAccountController {

    private final UserAccountService userService;

    @Autowired
    public UserAccountController(UserAccountService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserAccount>> getUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @PostMapping
    public void registerNewUser(@RequestBody UserAccount user) {
        userService.signupNewUser(user);
    }

    @DeleteMapping( path = "{studentID}")
    public void deleteUser(@PathVariable("studentID") Long id) {
        userService.deleteUser(id);
    }

    @PutMapping
    public void updateUser(
        @PathVariable("id") Long id,
        @RequestParam(required = false) String username,
        @RequestParam(required = false) String email) {

        userService.updateUser(id, username, email);

    }
}
