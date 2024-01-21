package com.bamate.bamatebackend.account;

public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException() {
        super("Could not find account");
    }

    public AccountNotFoundException(String username) {
        super("Could not find account " + username);
    }
}
