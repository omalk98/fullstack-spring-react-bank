package com.bank.backend.userAccount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 *
 */
@Repository
@Transactional(readOnly = true)
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

    @Query("SELECT u FROM UserAccount u WHERE u.email = ?1")
    Optional<UserAccount> findUserAccountByEmail(String email);

    @Transactional
    @Modifying
    @Query("""
            UPDATE UserAccount a
            SET a.enabled = TRUE WHERE a.email = ?1""")
    int enableUserAccount(String email);
}
