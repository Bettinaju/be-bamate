package com.bamate.bamatebackend.account;

import com.bamate.bamatebackend.account.models.Account;
import com.bamate.bamatebackend.account.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Interface representing a repository for managing {@code Account} entities.
 * Extends JpaRepository to inherit basic CRUD operations.
 */
public interface AccountRepository extends JpaRepository<Account, Long> {
    /**
     * Finds {@code accounts} by their {@code role}.
     * @param role The role to filter accounts by.
     * @return A list of accounts with the specified role.
     */
    List<Account> findByRole(Role role);

    /**
     * Finds an {@code account} by its {@code email}.
     * @param email The {@code email} of the {@code account} to find.
     * @return An optional containing the {@code account} with the specified {@code email}, if found.
     */
    Optional<Account> findByEmail(String email);

    /**
     * Deletes an {@code account} by its {@code email}.
     * @param email The provided {@code email} of the {@code account} that is to be deleted.
     */
    void deleteByEmail(String email);

}
