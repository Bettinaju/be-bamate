package com.bamate.bamatebackend.supervisor;

import com.bamate.bamatebackend.supervisor.models.Supervisor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * {@code SupervisorRepository} interface provides methods to perform CRUD operations on {@code Supervisor} entities in the database.
 */
public interface SupervisorRepository extends JpaRepository <Supervisor, Long> {
    /**
     * Retrieves a {@code Supervisor} by their {@code email}.
     * @param email The {@code email} of the {@code Supervisor} to retrieve.
     * @return An Optional containing the {@code Supervisor} with the specified {@code email}, if found.
     */
    Optional<Supervisor> findByEmail(String email);
}
