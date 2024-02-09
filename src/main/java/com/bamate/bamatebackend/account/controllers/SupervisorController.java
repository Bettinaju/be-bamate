package com.bamate.bamatebackend.account.controllers;

import com.bamate.bamatebackend.supervisor.SupervisorRepository;
import com.bamate.bamatebackend.supervisor.models.Supervisor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller responsible for handling supervisor-related endpoints.
 * This controller manages operations related to supervisors, such as listing {@code supervisor} accounts.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/supervisors")
public class SupervisorController {
    private final SupervisorRepository repository;


    /**
     * Retrieves a list of supervisor accounts.
     *
     * @return A list of {@code Supervisor} objects representing the supervisor accounts.
     */
    // List content endpoint
    @GetMapping()
    public List<Supervisor> supervisorAccounts() {
        return repository.findAll();
    }
}