package com.bamate.bamatebackend.account.controllers;

import com.bamate.bamatebackend.account.AccountRepository;
import com.bamate.bamatebackend.account.models.Account;
import com.bamate.bamatebackend.account.models.Role;
import com.bamate.bamatebackend.supervisor.models.Supervisor;
import com.bamate.bamatebackend.supervisor.SupervisorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

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
    private final SupervisorRepository supervisorRepository; // Added SupervisorRepository
    private final PasswordEncoder passwordEncoder;

    /**
     * Endpoint for retrieving all accounts.
     * Retrieves a list of all accounts from the repository.
     * @return A list of {@code Account} objects representing all accounts.
     */
    @GetMapping
    public List<Account> allAccount() {
        return repository.findAll();
    }

    /**
     * Creates a new account.
     *
     * @param newAccount The account object representing the new account to be created.
     * @return A {@code ResponseEntity} object representing the HTTP response, with the created account
     *         if the operation was successful, or an error message with the corresponding HTTP status code
     *         if the operation failed.
     */
    @PostMapping
    public ResponseEntity<?> createAccount(@RequestBody Account newAccount) {
        try {
            newAccount.setPassword(passwordEncoder.encode("WelcomeBamateUser!"));
            if (newAccount.getRole() == Role.SUPERVISOR)  {   // Check if role is Supervisor
                Supervisor supervisor = new Supervisor(newAccount); // Create Supervisor from Account
                supervisorRepository.save(supervisor);         // Save in SupervisorRepository
            } else {
                Account savedAccount = repository.save(newAccount);
                return new ResponseEntity<>(savedAccount, HttpStatus.CREATED);
            }
        } catch (DataIntegrityViolationException ex) {
            return new ResponseEntity<>("E-Mail-Adresse bereits registriert", HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok("Account created"); // Added this just to fulfil the method return type when the role is Supervisor
    }
}