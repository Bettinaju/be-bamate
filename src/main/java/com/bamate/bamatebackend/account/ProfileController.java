package com.bamate.bamatebackend.account;

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
    @GetMapping("/{id}")
    Account one(@PathVariable Long id) {

        return accountRepository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    Supervisor replaceSupervisorProfile(@RequestBody Supervisor newSupervisorProfile, @PathVariable Long id) {
        Supervisor oldSupervisorProfile = supervisorRepository.findById(id).orElseThrow(() -> new AccountNotFoundException(id));
        oldSupervisorProfile.setDescription(newSupervisorProfile.getDescription());
        return supervisorRepository.save(oldSupervisorProfile);
    }

    @DeleteMapping("/{id}")
    void deleteAccount(@PathVariable Long id) {
        accountRepository.deleteById(id);
    }
}
