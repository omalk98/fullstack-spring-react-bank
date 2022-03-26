package com.bank.backend.transaction;

import com.bank.backend.bankaccount.BankAccount;
import com.bank.backend.interfaces.IDepositable;
import com.bank.backend.interfaces.IWithdrawable;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
@Table
public class Transaction {
    @Id
    @SequenceGenerator(
            name = "transaction_sequence",
            sequenceName = "transaction_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "transaction_sequence"
    )
    private Long id;
    private Double value;
    private final BankAccount source;
    private final BankAccount destination;
    private TransactionType type;

    public Transaction() {

    }

    @Autowired
    public Transaction(Double value, BankAccount source, BankAccount destination, TransactionType type) {
        this.value = value;
        this.source = source;
        this.destination = destination;
        this.type = type;
    }

    public Transaction(Long id, Double value, BankAccount source, BankAccount destination, TransactionType type) {
        this.id = id;
        this.value = value;
        this.source = source;
        this.destination = destination;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Long getSource() {
        return source;
    }

    public void setSource(Long from) {
        this.source = from;
    }

    public Long getDestination() {
        return destination;
    }

    public void setDestination(Long to) {
        this.destination = to;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", value=" + value +
                ", from=" + source +
                ", to=" + destination +
                ", type=" + type +
                '}';
    }
}
