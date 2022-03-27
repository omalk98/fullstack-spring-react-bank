package com.bank.backend.customer;
import com.bank.backend.bankaccount.BankAccount;
import com.bank.backend.useraccount.UserAccount;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table
public class Customer {

    @Id
    @Column(name = "id")
    private Long id;

    @OneToOne()
    @MapsId
    @JoinColumn(name = "id")
    private UserAccount userAccount;

    @ManyToMany(mappedBy = "customers", fetch = FetchType.LAZY)
    private Set<BankAccount> bankAccounts = new HashSet<>();

    public Set<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(Set<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }
}
