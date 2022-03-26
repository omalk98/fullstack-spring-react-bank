package com.bank.backend.interfaces;

public interface IVerifiable {
    public abstract boolean verify(String email, String password);
}
