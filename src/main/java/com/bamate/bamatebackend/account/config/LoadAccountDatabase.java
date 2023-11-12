package com.bamate.bamatebackend.account.config;

import com.bamate.bamatebackend.account.AccountRepository;
import com.bamate.bamatebackend.account.models.Account;

import com.bamate.bamatebackend.account.models.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadAccountDatabase {
    private static final Logger log = LoggerFactory.getLogger(LoadAccountDatabase.class);

    @Bean
    CommandLineRunner initAccountDatabase(AccountRepository repository) {
        return args -> {
            log.info("Preloading " + repository.save(new Account("betti@testl.com", "Betti", "12345678", Role.SUPERVISOR)));
        };
    }

}
