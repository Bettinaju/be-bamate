package com.bamate.bamatebackend.account;

import com.bamate.bamatebackend.account.models.Account;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountRepository repository;

    AccountController(AccountRepository repository) { this.repository = repository; }

    @PostMapping
    Account newAccount(@RequestBody Account newAccount) {
        return repository.save(newAccount);
    }

    @GetMapping
    public List<Account> allAccount() {
        System.out.println("sfdklj");
        return repository.findAll();
    }

    // Single item
    @GetMapping("/{id}")
    Account one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new AccountNotFoundException(id));
    }

    @DeleteMapping("/{id}")
    void deleteEmployee(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
