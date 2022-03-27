package com.bank.backend.bankaccount;

//import com.bank.backend.interfaces.IDepositable;
//import com.bank.backend.interfaces.IWithdrawable;
//import com.bank.backend.transaction.Transaction;

import com.bank.backend.customer.Customer;

import javax.persistence.*;

@Entity
@Table
public /*abstract*/ class BankAccount /*implements IWithdrawable, IDepositable*/ {

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

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public BankAccount() {
    }
    //    @ManyToOne
//    private Customer customer;
//    @OneToMany
//    private List<Transaction> transactions;

//    @Override
//    public void deposit(BankAccount bankAccount) {
//        System.out.println("Withdrawing from BankAccount..." + bankAccount.id);
//    }

//    @Override
//    public void withdraw(BankAccount bankAccount) {
//        System.out.println("Withdrawing from BankAccount..." + bankAccount.id);
//    }

}

