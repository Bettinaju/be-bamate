package com.bamate.bamatebackend.account;

import com.bamate.bamatebackend.account.models.Account;
import com.bamate.bamatebackend.account.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByRole(Role role);
    Account findByEmail(String email);
}
