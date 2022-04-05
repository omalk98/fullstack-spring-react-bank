package com.bank.backend.bankaccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.swing.text.html.Option;
import java.util.Optional;
@Service
public class BankAccountService {
    private final BankAccountRepository bankAccountRepository;
    @Autowired
    public BankAccountService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    public Double getBalance() {
        Optional<BankAccount> bankAccount =
                bankAccountRepository.findById(1L);
        return bankAccount.orElseThrow().getBalance();
    }
}