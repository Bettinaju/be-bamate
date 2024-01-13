package com.bamate.bamatebackend.account;

import com.bamate.bamatebackend.account.models.Account;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    private final AccountRepository repository;

    ProfileController(AccountRepository repository) { this.repository = repository; }
    @GetMapping("/{id}")
    Account one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id));
    }

    @PutMapping("/{id}")
    // TODO add endpoint to save profile changes

    @DeleteMapping("/{id}")
    void deleteAccount(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
