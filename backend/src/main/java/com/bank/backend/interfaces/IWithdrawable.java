package com.bank.backend.interfaces;

@FunctionalInterface
public interface IWithdrawable {
    void withdraw(IDepositable bankAccount);
}
