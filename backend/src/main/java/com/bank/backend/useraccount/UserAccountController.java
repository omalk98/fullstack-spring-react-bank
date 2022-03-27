package com.bank.backend.useraccount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<UserAccount> getUsers() {
        return userService.getUsers();
    }

    @PostMapping
    public void registerNewUser(@RequestBody UserAccount user) {
        userService.signupNewUser(user);
    }

    @DeleteMapping( path = "{studentID}")
    public void deleteUser(@PathVariable("studemtID") Long id) {
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
