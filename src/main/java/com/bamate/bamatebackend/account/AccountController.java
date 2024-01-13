package com.bamate.bamatebackend.account;

import com.bamate.bamatebackend.account.models.Account;
import com.bamate.bamatebackend.account.models.Role;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountRepository repository;

    AccountController(AccountRepository repository) { this.repository = repository; }

    @GetMapping
    public List<Account> allAccount() {
        return repository.findAll();
    }


}
