package com.bamate.bamatebackend.account;

import com.bamate.bamatebackend.account.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
