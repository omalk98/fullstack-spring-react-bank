package com.bank.backend.interfaces;

@FunctionalInterface
public interface IDepositable {
    void deposit(IWithdrawable bankAccount);
}
