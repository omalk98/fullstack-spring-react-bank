package com.bank.backend.transaction;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
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
    private Long source;
    private Long destination;
    private TransactionType type;

    public Transaction(Double value, Long source, Long destination, TransactionType type) {
        this.value = value;
        this.source = source;
        this.destination = destination;
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
