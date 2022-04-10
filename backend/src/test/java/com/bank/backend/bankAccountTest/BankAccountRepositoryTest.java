package com.bank.backend.bankAccountTest;

import com.bank.backend.bankaccount.BankAccount;
import com.bank.backend.interfaces.BankAccountRepository;
import com.bank.backend.interfaces.UserAccountRepository;
import com.bank.backend.transaction.Transaction;
import com.bank.backend.transaction.TransactionType;
import com.bank.backend.userAccount.UserAccount;
import com.bank.backend.security.access.UserAccountRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class BankAccountRepositoryTest {
    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    UserAccountRepository userAccountRepository;

    @Test
    public void createBankAccount(){
        BankAccount bc1 = new BankAccount(53.2, null);
        BankAccount bc2 = new BankAccount(34.2, null);
        BankAccount bc3 = new BankAccount(23.4, null);

        BankAccount bc4 = new BankAccount(100000.0, List.of(new Transaction(23.34, TransactionType.WITHDRAW)));

        BankAccount bc5 = new BankAccount(200000.0, null);

        UserAccount ua1 = new UserAccount("Omar", "Hussein",
                "ohussein2@myseneca.ca", "binAdmin",
                new BCryptPasswordEncoder().encode("12345"),
                LocalDate.of(2020, 2, 25),
                UserAccountRole.ADMIN, List.of(bc1, bc2, bc3));

        UserAccount ua2 = new UserAccount("Soham", "Thaker",
                "sthaker@myseneca.ca", "belFast",
                new BCryptPasswordEncoder().encode("12345"),
                LocalDate.of(2020, 2, 25),
                UserAccountRole.ADMIN, List.of(bc4));

        UserAccount ua3 = new UserAccount("Philippe", "Cormier",
                "pcormier3@myseneca.ca", "bigBrain",
                new BCryptPasswordEncoder().encode("12345"),
                LocalDate.of(2020, 2, 25),
                UserAccountRole.ADMIN, List.of(bc5));

        UserAccount ua4 = new UserAccount("Sam", "Tucker",
                "sam@email.ca", "bigBrain",
                new BCryptPasswordEncoder().encode("12345"),
                LocalDate.of(2020, 2, 25),
                UserAccountRole.ADMIN, null);

        userAccountRepository.saveAll(List.of(ua1, ua2, ua3, ua4));
        //bankAccountRepository.saveAll(List.of(bc1, bc2, bc3));
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
        Optional<BankAccount> bankAccountList = bankAccountRepository.findById(4L);
        System.out.println(bankAccountList);
    }

//    @Test
//    public void getAllBankAccounts(){
//        List<BankAccount> bankAccountList = bankAccountRepository.getAllBankAccountsByID(1);
//        System.out.println(bankAccountList);
//    }
}