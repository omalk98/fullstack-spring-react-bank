package com.bank.backend.bankaccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/bankAccount")
@CrossOrigin("*")
public class BankAccountController {
    private final BankAccountService bankAccountService;
    @Autowired
    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping(path = "checkBalance")
    public Double getBalance(Long acctNum) {
        return bankAccountService.getBalance(acctNum);
    }

    @GetMapping(path = "getAllBankAccount")
    public ResponseEntity<List<BankAccount>> getAllBankAccounts(Long userId) {
        return new ResponseEntity<>(bankAccountService.getAllBankAccounts(userId),
                                    HttpStatus.OK);
    }

    @PutMapping(path = "deposit")
    public boolean deposit(Long acctNum, Double amount){
        return bankAccountService.deposit(acctNum,amount);
    }

    @PutMapping(path = "withdraw")
    public boolean withdraw(Long acctNum, Double amount){
        return bankAccountService.withdraw(acctNum, amount);
    }

    @PutMapping(path = "transfer")
    public boolean transfer(Long from, Long to, Double amount){
        return bankAccountService.transfer(from, to, amount);
    }

}