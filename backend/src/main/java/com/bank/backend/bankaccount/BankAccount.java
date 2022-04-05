package com.bank.backend.bankaccount;

//import com.bank.backend.interfaces.IDepositable;
//import com.bank.backend.interfaces.IWithdrawable;
//import com.bank.backend.transaction.Transaction;

import com.bank.backend.transaction.Transaction;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table
public class BankAccount /*implements IWithdrawable, IDepositable*/ {

    @Id
    //@Column(name = "account_number")
    @SequenceGenerator(
            name = "bankAccount_sequence",
            sequenceName = "bankAccount_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "bankAccount_sequence"
    )
    private Long accountNumber;
    private Double balance;

    @OneToMany(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name="bank_account",
            referencedColumnName = "accountNumber"
    )
    private List<Transaction> transactions;

    public BankAccount(Double balance){
        this.balance = balance;
    }

    public BankAccount(Double balance, List<Transaction> transactions){
        this.balance = balance;
        this.transactions = transactions;
    }

    //private Customer owner;

    //for withdraw
    //private Integer dailyLimit;

    //for withdraw
    //private Integer MonthlyLimit;

    //decide all the attributes & functions that you need for BankAccount
    //perform crud operations for the BankAccount in JUnit Tests
    //define relationships between Transaction and UserAccount classes

//    @Override
//    public void deposit(BankAccount bankAccount) {
//        System.out.println("Withdrawing from BankAccount..." + bankAccount.id);
//    }
//
//    @Override
//    public void withdraw(BankAccount bankAccount) {
//        System.out.println("Withdrawing from BankAccount..." + bankAccount.id);
//    }

}

