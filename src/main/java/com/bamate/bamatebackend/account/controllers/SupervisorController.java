package com.bamate.bamatebackend.account.controllers;

import com.bamate.bamatebackend.account.AccountRepository;
import com.bamate.bamatebackend.account.models.Account;
import com.bamate.bamatebackend.account.models.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller responsible for handling supervisor-related endpoints.
 * This controller manages operations related to supervisors, such as listing {@code supervisor} accounts.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/supervisors")
public class SupervisorController {
    private final AccountRepository repository;


    /**
     * Endpoint for listing {@code supervisor} accounts.
     * Retrieves a list of {@code supervisor} accounts from the repository and converts them to SupervisorDTO objects.
     * @return A list of SupervisorDTO objects representing {@code supervisor} accounts.
     */
    @GetMapping()
    public List<Account> supervisorAccounts() {
        return repository.findByRole(Role.SUPERVISOR);
    }
}
