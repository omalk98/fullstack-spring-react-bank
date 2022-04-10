package com.bank.backend.interfaces;

import com.bank.backend.bankaccount.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

    @Modifying
    @Query("update BankAccount b set b.balance = ?2 where b.accountNumber = ?1")
    int updateBalance(Long accountNumber, Double balance);

    @Query(value = "select * from bank_account b where b.user_account = ?1", nativeQuery = true)
    List<BankAccount> getAllBankAccountsByID(Long userId);

    @Query(value = "select balance from bank_account b where b.account_number = ?1", nativeQuery = true)
    Double getAllBalanceByAccountNumber(Long id);
}