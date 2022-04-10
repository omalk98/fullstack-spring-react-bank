package com.bank.backend.transaction;

import com.bank.backend.bankaccount.BankAccount;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table
public class Transaction implements Serializable {
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
    private String transactionType;

    @ManyToOne
    @JoinColumn(name = "source")
    private BankAccount source;

    @ManyToOne
    @JoinColumn(name = "destination")
    private BankAccount destination;

    public Transaction(Double value, BankAccount source,
                       BankAccount destination, TransactionType transactionType) {
        this.value = value;
        this.source = source;
        this.destination = destination;
        if(transactionType == TransactionType.TRANSFER){
            this.transactionType = "Transfer";
        }
        else if(transactionType == TransactionType.DEPOSIT){
            this.transactionType = "Deposit";
        }
        else{
            this.transactionType = "Withdraw";
        }
    }
}
