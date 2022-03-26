package com.bank.backend.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="trans")
@CrossOrigin("*")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<Transaction> getUsers() {
        return transactionService.getTransactions();
    }

    @PostMapping
    public void registerNewUser(@RequestBody Transaction transaction) {
        transactionService.addNewTransaction(transaction);
    }
}
