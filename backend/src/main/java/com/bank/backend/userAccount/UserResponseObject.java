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
    private List<BankAccount> list;
}
