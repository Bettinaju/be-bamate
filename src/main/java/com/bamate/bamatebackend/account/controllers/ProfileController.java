package com.bamate.bamatebackend.account.controllers;

import com.bamate.bamatebackend.account.AccountNotFoundException;
import com.bamate.bamatebackend.account.AccountRepository;
import com.bamate.bamatebackend.account.SupervisorRepository;
import com.bamate.bamatebackend.account.models.Account;
import com.bamate.bamatebackend.account.models.Supervisor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    private final AccountRepository accountRepository;
    private final SupervisorRepository supervisorRepository;

    ProfileController(AccountRepository accountRepository, SupervisorRepository supervisorRepository) {
        this.accountRepository = accountRepository;
        this.supervisorRepository = supervisorRepository;
    }

    // endpoint to show profile information
    @GetMapping("/{id}")
    Account one(@PathVariable Long id) {

        return accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id));
    }

    // endpoint to update profile information of supervisor
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    Supervisor replaceSupervisorProfile(@RequestBody Supervisor newSupervisorProfile, @PathVariable Long id) {
        Supervisor oldSupervisorProfile = supervisorRepository.findById(id).orElseThrow(() -> new AccountNotFoundException(id));
        oldSupervisorProfile.setDescription(newSupervisorProfile.getDescription());
        return supervisorRepository.save(oldSupervisorProfile);
    }

    // endpoint to delete a profile, complete account will be deleted
    @DeleteMapping("/{id}")
    void deleteAccount(@PathVariable Long id) {
        accountRepository.deleteById(id);
    }
}
