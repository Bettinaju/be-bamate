package com.bamate.bamatebackend.account.controllers;

import com.bamate.bamatebackend.account.AccountNotFoundException;
import com.bamate.bamatebackend.supervisor.SupervisorRepository;
import com.bamate.bamatebackend.account.models.Account;
import com.bamate.bamatebackend.supervisor.models.Supervisor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/profile")
public class ProfileController {

    private final SupervisorRepository supervisorRepository;

    // endpoint to show profile information
    @GetMapping("/{email}")
    Account one(@PathVariable String email) {
        return supervisorRepository.findByEmail(email)
                .orElseThrow(AccountNotFoundException::new);
    }

    // endpoint to update profile information of supervisor
    @PutMapping("/{email}")
    @ResponseStatus(HttpStatus.OK)
    Supervisor replaceSupervisorProfile(@RequestBody Supervisor newSupervisorProfile, @PathVariable String email) {
        Supervisor oldSupervisorProfile = supervisorRepository.findByEmail(email).orElseThrow(AccountNotFoundException::new);

        oldSupervisorProfile.setDescription(newSupervisorProfile.getDescription());
        oldSupervisorProfile.setAvailability(newSupervisorProfile.isAvailable());

        oldSupervisorProfile.getInterests().clear();
        oldSupervisorProfile.getInterests().addAll(newSupervisorProfile.getInterests());

        return supervisorRepository.save(oldSupervisorProfile);
    }

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
