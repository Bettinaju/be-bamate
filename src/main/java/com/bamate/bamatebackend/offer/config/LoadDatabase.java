package com.bamate.bamatebackend.offer.config;

import com.bamate.bamatebackend.offer.models.Degree;
import com.bamate.bamatebackend.offer.models.Offer;
import com.bamate.bamatebackend.offer.OfferRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(OfferRepository repository) {
        // String email, String name, Degree degree, String description, Boolean isAvailable
        return args -> {
            log.info("Preloading " + repository.save(new Offer("damaris@testl.com", "Damaris Test", Degree.BACHELOR, "I like turtles", false)));
            log.info("Preloading " + repository.save(new Offer("betti@testl.com", "Betti Test", Degree.DOCTORATE, "Dolphin on wheels", true)));
        };
    }
}