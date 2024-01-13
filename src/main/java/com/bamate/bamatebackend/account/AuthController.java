package com.bamate.bamatebackend.account;

import com.bamate.bamatebackend.account.models.Account;
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
    @PostMapping
    Account newAccount(@RequestBody Account newAccount) {
        return repository.save(newAccount);
    }

    // TODO Login endpoint
}
