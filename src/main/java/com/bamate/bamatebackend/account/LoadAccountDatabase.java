package com.bamate.bamatebackend.account;

import com.bamate.bamatebackend.account.models.Account;

import com.bamate.bamatebackend.account.models.Role;
import com.bamate.bamatebackend.supervisor.models.Interest;
import com.bamate.bamatebackend.supervisor.models.Supervisor;
import com.bamate.bamatebackend.supervisor.SupervisorRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class LoadAccountDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadAccountDatabase.class);

    private final PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner initAccountDatabase(AccountRepository accountRepository, SupervisorRepository supervisorRepository) {
        return args -> {
            log.info("Preloading " + accountRepository.save(new Account("dami@test.com", "Dami", "Li", passwordEncoder.encode("12345678"), Role.STUDENT)));
            log.info("Preloading " + accountRepository.save(new Account("freiheit@test.com", "Jörn", "Freiheit", passwordEncoder.encode("12345678"), Role.ADMIN)));

            Set<Interest> bettiInterests = EnumSet.of(Interest.WEB_DEVELOPMENT, Interest.DATENBANKEN);
            log.info("Preloading " + supervisorRepository.save(new Supervisor("betti@test.com", "Betti", "Ju", passwordEncoder.encode("12345678"), Role.SUPERVISOR, "Betti's Description", bettiInterests)));

            Set<Interest> aliceInterests = EnumSet.of(Interest.DATENBANKEN);
            log.info("Preloading " + supervisorRepository.save(new Supervisor("alice@test.com", "Alice", "Boe", passwordEncoder.encode("12345678"), Role.SUPERVISOR, "Alice's Description", aliceInterests)));

            Supervisor notAvailableSupervisor = new Supervisor("jana@test.com", "Jana", "Ko", passwordEncoder.encode("12345678"), Role.SUPERVISOR, "Jana's Description - I am not available", Collections.emptySet());
            notAvailableSupervisor.setAvailability(false);
            log.info("Preloading " + supervisorRepository.save(notAvailableSupervisor));
        };
    }

}
