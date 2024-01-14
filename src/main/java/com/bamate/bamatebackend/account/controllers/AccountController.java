package com.bamate.bamatebackend.account.controllers;

import com.bamate.bamatebackend.account.AccountRepository;
import com.bamate.bamatebackend.account.models.Account;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// TODO: Check if necessary
@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountRepository repository;

    AccountController(AccountRepository repository) { this.repository = repository; }

    // send all accounts
    @GetMapping
    public List<Account> allAccount() {
        return repository.findAll();
    }


}
