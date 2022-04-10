package com.bank.backend.bankAccountTest;

import com.bank.backend.bankaccount.BankAccount;
import com.bank.backend.bankaccount.BankAccountService;
import com.bank.backend.interfaces.BankAccountRepository;
import com.bank.backend.interfaces.TransactionRepository;
import com.bank.backend.interfaces.UserAccountRepository;
import com.bank.backend.transaction.Transaction;
import com.bank.backend.transaction.TransactionService;
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

    @Autowired
    BankAccountService bankAccountService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionService transactionService = new TransactionService(transactionRepository);

    @Test
    public void createBankAccount(){

        BankAccount bc1 = new BankAccount(53.2);
        BankAccount bc2 = new BankAccount(34.2);
        BankAccount bc3 = new BankAccount(23.4);

        BankAccount bc4 = new BankAccount(100000.0);

        BankAccount bc5 = new BankAccount(200000.0);

        Transaction tr1 = new Transaction(34.23, bc1, bc2, TransactionType.TRANSFER);
        Transaction tr2 = new Transaction(45.45, bc2, null, TransactionType.DEPOSIT);
        Transaction tr3 = new Transaction(12.12, null, bc3, TransactionType.WITHDRAW);

        transactionService.addTransaction(tr1);
        transactionService.addTransaction(tr2);
        transactionService.addTransaction(tr3);

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

    @Test
    public void getAllBankAccounts(){
        List<BankAccount> bankAccountList = bankAccountRepository.getAllBankAccountsByID(1L);
        System.out.println(bankAccountList);
    }
}