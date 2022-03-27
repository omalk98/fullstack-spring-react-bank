//package com.bank.backend.bankaccount;
//
//import com.bank.backend.customer.Customer;
//import com.bank.backend.interfaces.IDepositable;
//import com.bank.backend.interfaces.IWithdrawable;
//import com.bank.backend.transaction.Transaction;
//
//import javax.persistence.*;
//import java.util.List;
//
//@Entity
//@Table
//public abstract class BankAccount implements IWithdrawable, IDepositable {
//
//    @Id
//    @SequenceGenerator(
//            name = "bankAccount_sequence",
//            sequenceName = "bankAccount_sequence",
//            allocationSize = 1
//    )
//    @GeneratedValue(
//            strategy = GenerationType.SEQUENCE,
//            generator = "bankAccount_sequence"
//    )
//    private Long id;
//    @ManyToOne
//    private Customer customer;
//    @OneToMany
//    private List<Transaction> transactions;
//
//    @Override
//    public void deposit(BankAccount bankAccount) {
//        System.out.println("Withdrawing from BankAccount..." + bankAccount.id);
//    }
//
//    @Override
//    public void withdraw(BankAccount bankAccount) {
//        System.out.println("Withdrawing from BankAccount..." + bankAccount.id);
//    }
//
//}
