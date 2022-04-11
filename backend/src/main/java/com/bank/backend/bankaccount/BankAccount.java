package com.bank.backend.bankaccount;

import com.bank.backend.userAccount.UserAccount;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

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

    @ManyToOne
    @JoinColumn(name = "user_account")
    private UserAccount userAccount;

    public BankAccount(Double balance, UserAccount userAccount) {
        this.balance = balance;
        this.userAccount = userAccount;
    }
}

