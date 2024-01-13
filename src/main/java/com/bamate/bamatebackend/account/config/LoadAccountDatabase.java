package com.bamate.bamatebackend.account.config;

import com.bamate.bamatebackend.account.AccountRepository;
import com.bamate.bamatebackend.account.SupervisorRepository;
import com.bamate.bamatebackend.account.models.Account;

import com.bamate.bamatebackend.account.models.Role;
import com.bamate.bamatebackend.account.models.Supervisor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadAccountDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadAccountDatabase.class);

    @Bean
    CommandLineRunner initAccountDatabase(AccountRepository accountRepository, SupervisorRepository supervisorRepository) {
        return args -> {
            log.info("Preloading " + accountRepository.save(new Account("dami@test.com", "Dami", "Li", "12345678", Role.STUDENT)));
            log.info("Preloading " + accountRepository.save(new Account("freiheit@test.com", "Jörn", "Freiehit", "12345678", Role.ADMIN)));

            log.info("Preloading " + supervisorRepository.save(new Supervisor("betti@test.com", "Betti", "Ju", "12345678", Role.SUPERVISOR, "Bettis Description")));

            Supervisor notAvailableSupervisor = new Supervisor("jana@test.com", "Jana", "Ko", "12345678", Role.SUPERVISOR, "Janas Description - I am not available");
            notAvailableSupervisor.setAvailability(false);
            log.info("Preloading " + supervisorRepository.save(notAvailableSupervisor));

        };
    }

}
