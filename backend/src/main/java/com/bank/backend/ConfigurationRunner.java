//package com.bank.backend;
//
//import com.bank.backend.transaction.Transaction;
//import com.bank.backend.transaction.TransactionRepository;
//import com.bank.backend.transaction.TransactionType;
//import com.bank.backend.useraccount.UserAccount;
//import com.bank.backend.useraccount.UserAccountRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.time.LocalDate;
//import java.util.List;
//
//@Configuration
//public class ConfigurationRunner {
//
//    @Bean
//    CommandLineRunner commandLineRunner(UserAccountRepository repository, TransactionRepository repository2) {
//        return args -> {
//            UserAccount x = new UserAccount("Omar", "Hussein", "ohussein2@myseneca.ca", "binAdmin", "12345", LocalDate.of(2020, 2, 25));
//            UserAccount y = new UserAccount("Soham", "Thaker", "sthaker@myseneca.ca", "belFast", "12345", LocalDate.of(2020, 2, 25));
//            UserAccount z = new UserAccount("Philippe", "Cormier", "pcormier3@myseneca.ca", "bigBrain", "12345", LocalDate.of(2020, 2, 25));
//
//            Transaction a = new Transaction(44.44, 1L, 2L, TransactionType.WITHDRAW);
//            Transaction b = new Transaction(66.34, 2L, 3L, TransactionType.DEPOSIT);
//
//            repository.saveAll(List.of(x, y, z));
//            repository2.saveAll(List.of(a, b));
//        };
//    }
//}
