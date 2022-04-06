package com.bank.backend.interfaces;

import com.bank.backend.registration.token.ConformationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface ConfirmationTokenRepository extends JpaRepository<ConformationToken, Long> {

    @Query("SELECT c FROM ConformationToken c WHERE c.token = ?1")
    Optional<ConformationToken> findByToken(String token);

    @Transactional
    @Modifying
    @Query("UPDATE ConformationToken c " +
            "SET c.confirmedAt = ?2 " +
            "WHERE c.token = ?1")
    int updateConfirmedAt(String token, LocalDateTime confirmedAt);

}
