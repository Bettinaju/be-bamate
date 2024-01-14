package com.bamate.bamatebackend.account;

public class AccountNotFoundException extends RuntimeException{

   public AccountNotFoundException(Long id) {
        super("Could not find account " + id);
    }
}
