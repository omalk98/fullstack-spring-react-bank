package com.bank.backend.bankaccount;
import com.bank.backend.userAccount.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.NoSuchElementException;
@RestController
@RequestMapping(path = "bankAccount")
@CrossOrigin("*")
public class BankAccountController {
    private final BankAccountService bankAccountService;
    @Autowired
    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping(path = "checkBalance")
    public Double getBalance() {
        return bankAccountService.getBalance();
    }
}