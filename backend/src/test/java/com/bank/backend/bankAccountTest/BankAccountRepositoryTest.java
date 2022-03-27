package com.bank.backend.bankAccountTest;

import com.bank.backend.bankaccount.BankAccount;
import com.bank.backend.bankaccount.BankAccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BankAccountRepositoryTest {
    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Test
    public void saveBankAccount(){
        BankAccount bc1 = new BankAccount();
        BankAccount bc2 = new BankAccount();
        BankAccount bc3 = new BankAccount();

        bankAccountRepository.saveAll(List.of(bc1, bc2, bc3));
    }

    @Test
    public void printBankAccount(){
        List<BankAccount> bankAccountList = bankAccountRepository.findAll();
        System.out.println(bankAccountList);
    }
}