package com.bamate.bamatebackend.account.controllers;

import com.bamate.bamatebackend.account.AccountRepository;
import com.bamate.bamatebackend.account.models.Account;
import com.bamate.bamatebackend.account.models.LoginDetailsDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AccountRepository repository;
    AuthController(AccountRepository repository) { this.repository = repository; }

    // Registration endpoint
    @PostMapping("/register")
    Account newAccount(@RequestBody Account newAccount) {
        return repository.save(newAccount);
    }

    // Login endpoint
    @PostMapping("/login")
    ResponseEntity<String> login(@RequestBody LoginDetailsDTO loginRequest) {
        Account existingAccount = repository.findByEmail(loginRequest.getEmail());

        if (existingAccount != null && existingAccount.getPassword().equals(loginRequest.getPassword())) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid email or password");
        }
    }

}