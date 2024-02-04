package com.bamate.bamatebackend.account.controllers;

import com.bamate.bamatebackend.account.AccountRepository;
import com.bamate.bamatebackend.account.models.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {

    private final AccountRepository repository;
    private final PasswordEncoder passwordEncoder;

    // all accounts
    @GetMapping
    public List<Account> allAccount() {
        return repository.findAll();
    }

    // create new account
    @PostMapping
    public ResponseEntity<?> createAccount(@RequestBody Account newAccount) {
        try {
            newAccount.setPassword(passwordEncoder.encode("WelcomeBamateUser!"));
            Account savedAccount = repository.save(newAccount);
            return new ResponseEntity<>(savedAccount, HttpStatus.CREATED);
        } catch (DataIntegrityViolationException ex) {
            return new ResponseEntity<>("E-Mail-Adresse bereits registriert", HttpStatus.BAD_REQUEST);
        }
    }
}
