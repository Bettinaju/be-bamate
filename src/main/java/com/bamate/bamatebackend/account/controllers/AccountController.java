package com.bamate.bamatebackend.account.controllers;

import com.bamate.bamatebackend.account.AccountRepository;
import com.bamate.bamatebackend.account.models.Account;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller responsible for handling account-related endpoints.
 * This controller manages operations related to accounts, such as retrieving all accounts.
 */
// TODO: Check if necessary
@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountRepository repository;

    /**
     * Constructor for {@code AccountController}.
     * @param repository The repository used for accessing account data.
     */
    AccountController(AccountRepository repository) { this.repository = repository; }

    /**
     * Endpoint for retrieving all accounts.
     * Retrieves a list of all accounts from the repository.
     * @return A list of {@code Account} objects representing all accounts.
     */
    // send all accounts
    @GetMapping
    public List<Account> allAccount() {
        return repository.findAll();
    }
}
