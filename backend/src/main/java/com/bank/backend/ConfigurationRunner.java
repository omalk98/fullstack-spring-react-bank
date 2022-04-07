//package com.bank.backend;
//
//import com.bank.backend.transaction.Transaction;
//import com.bank.backend.interfaces.TransactionRepository;
//import com.bank.backend.transaction.TransactionType;
//import com.bank.backend.useraccount.UserAccount;
//import com.bank.backend.useraccount.UserAccountRepository;
//import com.bank.backend.useraccount.UserAccountRole;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
//
//            Transaction a = new Transaction(44.44, 1L, 2L, TransactionType.WITHDRAW);
//            Transaction b = new Transaction(66.34, 2L, 3L, TransactionType.DEPOSIT);
//
//            repository.saveAll(List.of(x, y, z));
//            repository2.saveAll(List.of(a, b));
//        };
//    }
//}
