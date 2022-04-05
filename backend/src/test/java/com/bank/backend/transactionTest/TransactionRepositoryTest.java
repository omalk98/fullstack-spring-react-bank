package com.bank.backend.transactionTest;

import com.bank.backend.bankaccount.BankAccount;
import com.bank.backend.bankaccount.BankAccountRepository;
import com.bank.backend.transaction.Transaction;
import com.bank.backend.transaction.TransactionRepository;
import com.bank.backend.transaction.TransactionType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TransactionRepositoryTest {
    @Autowired
    private TransactionRepository transactionRepository;

    @Test
    public void addTransaction(){

    }

}