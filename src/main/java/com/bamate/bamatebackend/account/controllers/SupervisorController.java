package com.bamate.bamatebackend.account.controllers;

import com.bamate.bamatebackend.account.AccountRepository;
import com.bamate.bamatebackend.account.models.Account;
import com.bamate.bamatebackend.account.models.Role;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/supervisors")
public class SupervisorController {
    private final AccountRepository repository;

    SupervisorController(AccountRepository repository) { this.repository = repository; }

    // List content endpoint
    @GetMapping()
    public List<Account> supervisorAccounts() {
        return repository.findByRole(Role.SUPERVISOR);
    }
}
