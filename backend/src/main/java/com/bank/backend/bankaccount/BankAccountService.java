package com.bank.backend.bankaccount;
import com.bank.backend.interfaces.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class BankAccountService {
    private final BankAccountRepository bankAccountRepository;
    @Autowired
    public BankAccountService(BankAccountRepository bankAccountRepository) {
        this.bankAccountRepository = bankAccountRepository;
    }

    //not used
    public Double getBalance(Long acctNum) {
        Optional<BankAccount> bankAccount =
                bankAccountRepository.findById(acctNum);
        return bankAccount.orElseThrow().getBalance();
    }

    public List<BankAccount> getAllBankAccounts(Long userId) {
        return bankAccountRepository.getAllBankAccountsByID(userId);
    }

    public boolean transfer(Long from, Long to, Float amount) {
        Double fromBalance = bankAccountRepository.getAllBalanceByAccountNumber(from);
        Double toBalance = bankAccountRepository.getAllBalanceByAccountNumber(to);

        if(fromBalance > amount) {
            fromBalance -= amount;
            toBalance += amount;

            int updatedFromRows = bankAccountRepository.updateBalance(from, fromBalance);
            int updatedToRows = bankAccountRepository.updateBalance(to, toBalance);

            return (updatedToRows + updatedFromRows) > 0;
        }

        return false;
    }

    public boolean deposit(Long acctNum, Float amount) {
        double updatedBalance = getBalance(acctNum) + amount;
        int updatedRows = bankAccountRepository.updateBalance(acctNum, updatedBalance);
        return updatedRows > 0;
    }

    public boolean withdraw(Long acctNum, Float amount) {
        double currentBalance = getBalance(acctNum);
        if(currentBalance > 0 && currentBalance > amount) {
            double updatedBalance = currentBalance > amount
                                    ? currentBalance - amount
                                    : amount - currentBalance;
            int updatedRows = bankAccountRepository.updateBalance(acctNum, updatedBalance);
            return updatedRows > 0;
        }
        return false;
    }
}