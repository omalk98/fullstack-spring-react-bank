package com.bank.backend.bankaccount;

import com.bank.backend.customer.Customer;
import com.bank.backend.interfaces.IDepositable;
import com.bank.backend.interfaces.IWithdrawable;
import com.bank.backend.transaction.Transaction;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import java.util.List;

public abstract class BankAccount implements IWithdrawable, IDepositable {

    @Id
    @SequenceGenerator(
            name = "bankAccount_sequence",
            sequenceName = "bankAccount_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "bankAccount_sequence"
    )
    private Long id;
    private Customer customer;
    private List<Transaction> transactions;

    @Override
    public void deposit(IWithdrawable bankAccount) {
        System.out.println("Withdrawing from BankAccount...");
    }

    @Override
    public void withdraw(IDepositable bankAccount) {
        System.out.println("Withdrawing from BankAccount...");
    }

}
