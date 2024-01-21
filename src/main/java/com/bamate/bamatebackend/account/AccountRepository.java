package com.bamate.bamatebackend.account;

import com.bamate.bamatebackend.account.models.Account;
import com.bamate.bamatebackend.account.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByRole(Role role);
    Optional<Account> findByEmail(String email);
    void deleteByEmail(String email);

}
