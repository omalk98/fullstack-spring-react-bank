package com.bank.backend.bankAccountTest;

import com.bank.backend.bankaccount.BankAccount;
import com.bank.backend.bankaccount.BankAccountRepository;
import com.bank.backend.transaction.Transaction;
import com.bank.backend.transaction.TransactionType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class BankAccountRepositoryTest {
    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Test
    public void createBankAccount(){
        BankAccount bc1 = new BankAccount(53.2);
        BankAccount bc2 = new BankAccount(34.2);
        BankAccount bc3 = new BankAccount(23.4);

        bankAccountRepository.saveAll(List.of(bc1, bc2, bc3));
    }

    @Test
    public void createBankAccountWithTransactions(){
        BankAccount bankAccount = new BankAccount(2000.0,
            List.of(
                new Transaction(500.0, TransactionType.WITHDRAW),
                new Transaction(700.0, TransactionType.WITHDRAW),
                new Transaction(440.0, TransactionType.WITHDRAW),
                new Transaction(1200.0, TransactionType.DEPOSIT),
                new Transaction(80.0, TransactionType.DEPOSIT)
            )
        );

        bankAccountRepository.save(bankAccount);
    }

    @Test
    public void updateBalance() {
        int updated = bankAccountRepository.updateBalance(1L, 23.23);
        if(updated > 0){
            System.out.println("Updated balance!");
        }
        else{
            System.out.println("Provided account number was not found!");
        }
    }

    @Test
    public void deleteBankAccount() {
        bankAccountRepository.deleteById(1L);
        System.out.println("User Account deleted!");
    }

    @Test
    public void readAllBankAccount(){
        List<BankAccount> bankAccountList = bankAccountRepository.findAll();
        System.out.println(bankAccountList);
    }

    @Test
    public void readOneBankAccount(){
        Optional<BankAccount> bankAccountList = bankAccountRepository.findById(1L);
        System.out.println(bankAccountList);
    }
}