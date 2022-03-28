package com.bank.backend.bankaccount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

    @Modifying
    @Query("update BankAccount b set b.balance = ?2 where b.accountNumber = ?1")
    int updateBalance(Long accountNumber, Double balance);
}
