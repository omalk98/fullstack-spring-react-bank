package com.bank.backend.bankaccount;

//import com.bank.backend.interfaces.IDepositable;
//import com.bank.backend.interfaces.IWithdrawable;
//import com.bank.backend.transaction.Transaction;

import com.bank.backend.customer.Customer;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
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
    private Long accountNumber;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinTable(name = "customer_bankAccount",
            joinColumns = {
                    @JoinColumn(name = "bank_account_number", referencedColumnName = "accountNumber",
                            nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "customer_id", referencedColumnName = "id",
                            nullable = false, updatable = false)})
    private Set<Customer> customers = new HashSet<>();

    public Set<Customer> getCustomer() {
        return customers;
    }

    public void setCustomer(Set<Customer> customer) {
        this.customers = customer;
    }

//    @Override
//    public void deposit(BankAccount bankAccount) {
//        System.out.println("Withdrawing from BankAccount..." + bankAccount.id);
//    }

//    @Override
//    public void withdraw(BankAccount bankAccount) {
//        System.out.println("Withdrawing from BankAccount..." + bankAccount.id);
//    }

}

