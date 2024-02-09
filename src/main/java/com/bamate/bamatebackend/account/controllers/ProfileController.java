package com.bamate.bamatebackend.account.controllers;

import com.bamate.bamatebackend.account.AccountNotFoundException;
import com.bamate.bamatebackend.supervisor.SupervisorRepository;
import com.bamate.bamatebackend.account.models.Account;
import com.bamate.bamatebackend.supervisor.models.Supervisor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller responsible for handling profile-related operations.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ProfileController {

    private final SupervisorRepository supervisorRepository;

    /**
     * Retrieves profile information for a given {@code email}.
     * @param email The {@code email} of the profile to retrieve.
     * @return The {@code Account} associated with the {@code email}.
     * @throws AccountNotFoundException if no {@code Account} is found with the given {@code email}.
     */
    @GetMapping("/{email}")
    Account one(@PathVariable String email) {
        return supervisorRepository.findByEmail(email)
                .orElseThrow(AccountNotFoundException::new);
    }

    /**
     * Updates the profile information of a {@code Supervisor}.
     * @param newSupervisorProfile The new profile information to replace the old one.
     * @param email The {@code email} of the {@code Supervisor} whose profile is to be updated.
     * @return The updated {@code Supervisor} profile.
     * @throws AccountNotFoundException if no {@code Supervisor} is found with the given {@code email}.
     */
    @PutMapping("/{email}")
    @ResponseStatus(HttpStatus.OK)
    Supervisor replaceSupervisorProfile(@RequestBody Supervisor newSupervisorProfile, @PathVariable String email) {
        Supervisor oldSupervisorProfile = supervisorRepository.findByEmail(email).orElseThrow(AccountNotFoundException::new);

        oldSupervisorProfile.setFirstName(newSupervisorProfile.getFirstName());
        oldSupervisorProfile.setLastName(newSupervisorProfile.getLastName());
        oldSupervisorProfile.setRecentJob(newSupervisorProfile.getRecentJob());
        oldSupervisorProfile.setPreviousJob(newSupervisorProfile.getPreviousJob());
        oldSupervisorProfile.setAvailability(newSupervisorProfile.isAvailable());
        oldSupervisorProfile.setJobReference(newSupervisorProfile.getJobReference());

        oldSupervisorProfile.getInterests().clear();
        oldSupervisorProfile.getInterests().addAll(newSupervisorProfile.getInterests());

        return supervisorRepository.save(oldSupervisorProfile);
    }

    /**
     * Deletes a supervisor's profile and account by {@code email}.
     * @param email The {@code email} of the {@code Supervisor} whose profile is to be deleted.
     * @return ResponseEntity with a message indicating success or failure of deletion.
     */
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
