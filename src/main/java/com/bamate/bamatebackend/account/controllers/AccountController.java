package com.bamate.bamatebackend.account.controllers;

import com.bamate.bamatebackend.account.AccountRepository;
import com.bamate.bamatebackend.account.models.Account;
import com.bamate.bamatebackend.account.models.Role;
import com.bamate.bamatebackend.supervisor.SupervisorRepository;
import com.bamate.bamatebackend.supervisor.models.Supervisor;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

/**
 * REST controller responsible for handling account-related endpoints.
 * This controller manages operations related to accounts, such as retrieving all accounts.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {

    private final AccountRepository repository;
    private final SupervisorRepository supervisorRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Endpoint for retrieving all accounts.
     * Retrieves a list of all accounts from the repository.
     *
     * @return A list of {@code Account} objects representing all accounts.
     */
    @GetMapping
    public List<Account> allAccount() {
        return repository.findAll();
    }

    /**
     * Endpoint for creating a new {@code account}.
     * Creates a new {@code account} based on the provided {@code account} details.
     *
     * @param newAccount The new {@code account} to be created.
     * @return A response entity containing the created {@code account} or an error message.
     */
    @PostMapping
    public ResponseEntity<?> createAccount(@RequestBody Account newAccount) {
        try {
            newAccount.setPassword(passwordEncoder.encode("WelcomeBamateUser!"));
            if (newAccount.getRole().equals(Role.SUPERVISOR)) {
                Supervisor supervisor = new Supervisor(newAccount.getEmail(), newAccount.getFirstName(), newAccount.getLastName(), newAccount.getPassword(), Role.SUPERVISOR, "", Collections.emptySet());
                Supervisor savedSupervisor = supervisorRepository.save(supervisor);
                return new ResponseEntity<>(savedSupervisor, HttpStatus.CREATED);
            } else {
                Account savedAccount = repository.save(newAccount);
                return new ResponseEntity<>(savedAccount, HttpStatus.CREATED);

            }
        } catch (DataIntegrityViolationException ex) {
            return new ResponseEntity<>("E-Mail-Adresse bereits registriert", HttpStatus.BAD_REQUEST);
        }
    }
}
