package com.bank.backend.transaction;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
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
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    public Transaction(Double value, TransactionType type) {
        this.value = value;
        this.type = type;
    }

//    @Override
//    public String toString() {
//        return "Transaction{" +
//                "id=" + id +
//                ", value=" + value +
//                ", type=" + type +
//                '}';
//    }
}
