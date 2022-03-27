<<<<<<< HEAD
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
    public List<Transaction> getTransaction() {
        return transactionService.getTransactions();
    }

    @PostMapping
    public void registerNewTransaction(@RequestBody Transaction transaction) {
        transactionService.addNewTransaction(transaction);
    }
}
=======
//package com.bank.backend.transaction;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping(path="trans")
//@CrossOrigin("*")
//public class TransactionController {
//
//    private final TransactionService transactionService;
//
//    @Autowired
//    public TransactionController(TransactionService transactionService) {
//        this.transactionService = transactionService;
//    }
//
//    @GetMapping
//    public List<Transaction> getTransactions() {
//        return transactionService.getTransactions();
//    }
//
//    @PostMapping
//    public void registerNewTransaction(@RequestBody Transaction transaction) {
//        transactionService.addNewTransaction(transaction);
//    }
//}
>>>>>>> 5cf0f54221d5e4870bc1c29cf2d99317eec7e8b2
