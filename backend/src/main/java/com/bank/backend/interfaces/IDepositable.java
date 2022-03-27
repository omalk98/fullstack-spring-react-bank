package com.bank.backend.interfaces;

import com.bank.backend.bankaccount.BankAccount;

public interface IDepositable {
    void deposit(BankAccount bankAccount);
}
