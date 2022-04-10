package com.bank.backend.bankaccount;
import com.bank.backend.interfaces.BankAccountRepository;
import com.bank.backend.interfaces.TransactionRepository;
import com.bank.backend.transaction.Transaction;
import com.bank.backend.transaction.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class BankAccountService {
    private final BankAccountRepository bankAccountRepository;
    private final TransactionRepository transactionRepository;

    @Autowired
    public BankAccountService(BankAccountRepository bankAccountRepository, TransactionRepository transactionRepository) {
        this.bankAccountRepository = bankAccountRepository;
        this.transactionRepository = transactionRepository;
    }

    public Double getBalance(Long acctNum) {
        Optional<BankAccount> bankAccount =
                bankAccountRepository.findById(acctNum);
        return bankAccount.orElseThrow().getBalance();
    }

    public List<BankAccount> getAllBankAccounts(Long userId) {
        return bankAccountRepository.getAllBankAccountsByID(userId);
    }

    public boolean transfer(Long from, Long to, Double amount) {
        Double fromBalance = bankAccountRepository.getAllBalanceByAccountNumber(from);
        Double toBalance = bankAccountRepository.getAllBalanceByAccountNumber(to);

        if(fromBalance > amount) {
            fromBalance -= amount;
            toBalance += amount;

            int updatedFromRows = bankAccountRepository.updateBalance(from, fromBalance);
            int updatedToRows = bankAccountRepository.updateBalance(to, toBalance);
            Optional<BankAccount> fromBA = bankAccountRepository.findById(from);
            Optional<BankAccount> toBA = bankAccountRepository.findById(to);

            if(updatedFromRows > 0
                    && updatedToRows > 0
                    && fromBA.isPresent()
                    && toBA.isPresent()) {

                Transaction tr = new Transaction(amount, fromBA.get(),
                        toBA.get(), TransactionType.TRANSFER);
                transactionRepository.save(tr);

                fromBA.get().setSourceTransactions(tr);
                toBA.get().setDestinationTransactions(tr);
            }

            return (updatedToRows + updatedFromRows) > 0;
        }

        return false;
    }

    public boolean deposit(Long acctNum, Double amount) {
        double updatedBalance = getBalance(acctNum) + amount;
        int updatedRows = bankAccountRepository.updateBalance(acctNum, updatedBalance);
        Optional<BankAccount> ba = bankAccountRepository.findById(acctNum);

        if(updatedRows > 0 && ba.isPresent()) {

            Transaction tr = new Transaction(amount, ba.get(),
                    null, TransactionType.DEPOSIT);
            transactionRepository.save(tr);

            ba.get().setSourceTransactions(tr);
        }

        return updatedRows > 0;
    }

    public boolean withdraw(Long acctNum, Double amount) {
        double currentBalance = getBalance(acctNum);
        if(currentBalance > 0 && currentBalance > amount) {
            double updatedBalance = currentBalance > amount
                                    ? currentBalance - amount
                                    : amount - currentBalance;
            int updatedRows = bankAccountRepository.updateBalance(acctNum, updatedBalance);
            Optional<BankAccount> ba = bankAccountRepository.findById(acctNum);

            if(updatedRows > 0 && ba.isPresent()) {
                Transaction tr = new Transaction(amount, null,
                        ba.get(), TransactionType.WITHDRAW);
                transactionRepository.save(tr);

                ba.get().setDestinationTransactions(tr);
            }

            return updatedRows > 0;
        }
        return false;
    }
}