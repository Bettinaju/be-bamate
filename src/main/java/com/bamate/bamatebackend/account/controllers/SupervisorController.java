package com.bamate.bamatebackend.account.controllers;

import com.bamate.bamatebackend.account.AccountRepository;
import com.bamate.bamatebackend.account.dto.SupervisorDTO;
import com.bamate.bamatebackend.account.models.Role;
import com.bamate.bamatebackend.supervisor.models.Supervisor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/supervisors")
public class SupervisorController {
    private final AccountRepository repository;

    SupervisorController(AccountRepository repository) {
        this.repository = repository;
    }

    // List content endpoint
    @GetMapping()
    public List<SupervisorDTO> supervisorAccounts() {
        return repository.findByRole(Role.SUPERVISOR).stream()
                .map(account -> convertToSupervisorDTO(new Supervisor(account)))
                .collect(Collectors.toList());
    }

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
