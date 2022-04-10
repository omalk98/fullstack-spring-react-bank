package com.bank.backend.bankaccount;

//import com.bank.backend.interfaces.IDepositable;
//import com.bank.backend.interfaces.IWithdrawable;
//import com.bank.backend.transaction.Transaction;

import com.bank.backend.transaction.Transaction;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
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
public class BankAccount implements Serializable {

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
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "source"
    )
    private List<Transaction> sourceTransactions = new ArrayList<>();

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "destination"
    )
    private List<Transaction> destinationTransactions = new ArrayList<>();

    public BankAccount(Double balance) {
        this.balance = balance;
    }

    public List<Transaction> getSourceTransactions() {
        return sourceTransactions;
    }

    public void setSourceTransactions(Transaction sourceTransactions) {
        this.sourceTransactions.add(sourceTransactions);
    }

    public List<Transaction> getDestinationTransactions() {
        return destinationTransactions;
    }

    public void setDestinationTransactions(Transaction destinationTransactions) {
        this.destinationTransactions.add(destinationTransactions);
    }
}

