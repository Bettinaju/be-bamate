package com.bamate.bamatebackend.account.controllers;

import com.bamate.bamatebackend.account.AccountNotFoundException;
import com.bamate.bamatebackend.account.AccountRepository;
import com.bamate.bamatebackend.supervisor.SupervisorRepository;
import com.bamate.bamatebackend.account.models.Account;
import com.bamate.bamatebackend.supervisor.models.Supervisor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller responsible for handling profile-related operations.
 */
@RestController
@RequestMapping("/profile")
public class ProfileController {
    // TODO: remove unused stuff - maybe restructure
    private final AccountRepository accountRepository;
    private final SupervisorRepository supervisorRepository;

    /**
     * Constructor for {@code ProfileController}.
     * @param accountRepository Repository for managing {@code Account} entities.
     * @param supervisorRepository Repository for managing {@code Supervisor} entities.
     */
    ProfileController(AccountRepository accountRepository, SupervisorRepository supervisorRepository) {
        this.accountRepository = accountRepository;
        this.supervisorRepository = supervisorRepository;
    }

    /**
     * Retrieves profile information for a given {@code email}.
     * @param email The {@code email} of the profile to retrieve.
     * @return The {@code Account} associated with the {@code email}.
     * @throws AccountNotFoundException if no {@code Account} is found with the given {@code email}.
     */
    // endpoint to show profile information
    @GetMapping("/{email}")
    Account one(@PathVariable String email) {
        return supervisorRepository.findByEmail(email)
                .orElseThrow(() -> new AccountNotFoundException());
    }

    /**
     * Updates the profile information of a {@code Supervisor}.
     * @param newSupervisorProfile The new profile information to replace the old one.
     * @param email The {@code email} of the {@code Supervisor} whose profile is to be updated.
     * @return The updated {@code Supervisor} profile.
     * @throws AccountNotFoundException if no {@code Supervisor} is found with the given {@code email}.
     */
    // endpoint to update profile information of supervisor
    @PutMapping("/{email}")
    @ResponseStatus(HttpStatus.OK)
    Supervisor replaceSupervisorProfile(@RequestBody Supervisor newSupervisorProfile, @PathVariable String email) {
        Supervisor oldSupervisorProfile = supervisorRepository.findByEmail(email).orElseThrow(() -> new AccountNotFoundException());

        oldSupervisorProfile.setDescription(newSupervisorProfile.getDescription());
        oldSupervisorProfile.setAvailability(newSupervisorProfile.isAvailable());

        oldSupervisorProfile.getInterests().clear();
        oldSupervisorProfile.getInterests().addAll(newSupervisorProfile.getInterests());

        return supervisorRepository.save(oldSupervisorProfile);
    }

    /**
     * Deletes a supervisor's profile and account by {@code email}.
     * @param email The {@code email} of the {@code Supervisor} whose profile is to be deleted.
     * @return ResponseEntity with a message indicating success or failure of deletion.
     */
    // endpoint to delete a profile, complete account will be deleted
    @DeleteMapping("/{email}")
    public ResponseEntity<String> deleteSupervisorByEmail(@PathVariable String email) {
        Supervisor supervisor = supervisorRepository.findByEmail(email).orElse(null);

        if (supervisor != null) {
            supervisorRepository.delete(supervisor);
            return new ResponseEntity<>("Supervisor with email " + email + " deleted successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Supervisor with email " + email + " not found.", HttpStatus.NOT_FOUND);
        }
    }
}
