package com.bank.backend.userAccount;

import com.bank.backend.bankaccount.BankAccount;
import com.bank.backend.security.access.UserAccountRole;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@Getter
public class UserResponseObject implements Serializable {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private UserAccountRole userRole;
    private Integer numAccounts;

    public UserResponseObject(UserAccount user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.username = user.getUsername();
        this.userRole = user.getUserRole();
        this.numAccounts = user.getList().size();
    }
}
