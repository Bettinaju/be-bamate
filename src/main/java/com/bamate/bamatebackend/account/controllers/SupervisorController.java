package com.bamate.bamatebackend.account.controllers;

import com.bamate.bamatebackend.account.AccountRepository;
import com.bamate.bamatebackend.account.dto.SupervisorDTO;
import com.bamate.bamatebackend.account.models.Role;
import com.bamate.bamatebackend.supervisor.models.Supervisor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * REST controller responsible for handling supervisor-related endpoints.
 * This controller manages operations related to supervisors, such as listing {@code supervisor} accounts.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/supervisors")
public class SupervisorController {
    private final AccountRepository repository;


    /**
     * Endpoint for listing {@code supervisor} accounts.
     * Retrieves a list of {@code supervisor} accounts from the repository and converts them to SupervisorDTO objects.
     * @return A list of SupervisorDTO objects representing {@code supervisor} accounts.
     */
    // List content endpoint
    @GetMapping()
    public List<SupervisorDTO> supervisorAccounts() {
        return repository.findByRole(Role.SUPERVISOR).stream()
                .map(account -> convertToSupervisorDTO(new Supervisor(account)))
                .collect(Collectors.toList());
    }
    /**
     * Converts a {@code supervisor} object to a SupervisorDTO object.
     * @param supervisor The {@code supervisor} object to convert.
     * @return A SupervisorDTO object representing the {@code supervisor}.
     */
    private SupervisorDTO convertToSupervisorDTO(Supervisor supervisor) {
        return new SupervisorDTO(
                supervisor.getId(),
                supervisor.getFirstName(),
                supervisor.getLastName(),
                supervisor.getEmail(),
                supervisor.getRole(),
                supervisor.getDescription(),
                supervisor.isAvailable(),
                supervisor.getInterests()
        );
    }
}
