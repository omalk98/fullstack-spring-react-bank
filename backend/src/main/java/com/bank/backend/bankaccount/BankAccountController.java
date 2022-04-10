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

    //not used
    //http://localhost:8080/api/bankAccount/checkBalance?acctNum=1
    //change acctNum accordingly and make sure it is Get request
    //you need a user_account(fk) as a param, for this function
    //and you will return
    @GetMapping(path = "checkBalance")
    public Double getBalance(Long acctNum) {
        return bankAccountService.getBalance(acctNum);
    }

    //http://localhost:8080/api/bankAccount/getAllBankAccount?userId=1
    @GetMapping(path = "getAllBankAccount")
    public ResponseEntity<List<BankAccount>> getAllBankAccounts(Long userId) {
        return new ResponseEntity<>(bankAccountService.getAllBankAccounts(userId),
                                    HttpStatus.OK);
    }

    //http://localhost:8080/api/bankAccount/deposit?acctNum=1&amount=23.23
    //change acctNum and amount accordingly and make sure it's a Put request
    @PutMapping(path = "deposit")
    public boolean deposit(Long acctNum, Double amount){
        return bankAccountService.deposit(acctNum,amount);
    }

    //http://localhost:8080/api/bankAccount/withdraw?acctNum=1&amount=23.23
    //change acctNum and amount accordingly and make sure it's a Put request
    @PutMapping(path = "withdraw")
    public boolean withdraw(Long acctNum, Double amount){
        return bankAccountService.withdraw(acctNum, amount);
    }

    //http://localhost:8080/api/bankAccount/transfer?from=4&to=5&amount=35000
    @PutMapping(path = "transfer")
    public boolean transfer(Long from, Long to, Double amount){
        return bankAccountService.transfer(from, to, amount);
    }
}