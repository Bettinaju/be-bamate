package com.bamate.bamatebackend.account;

/**
 * Custom exception class for handling cases where an {@code account} is not found.
 */
public class AccountNotFoundException extends RuntimeException {

    /**
     * Constructs an {@code AccountNotFoundException} with a default error message.
     */
    public AccountNotFoundException() {
        super("Could not find account");
    }

    /**
     * Constructs an {@code AccountNotFoundException} with a customized error message including the {@code username}.
     * @param username The {@code username} for which the {@code account} was not found.
     */
    public AccountNotFoundException(String username) {
        super("Could not find account " + username);
    }
}
