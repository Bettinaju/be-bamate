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

            // Supervisor Dummies

            Set<Interest> heinzInterests = EnumSet.of(Interest.Projektmanagement);
            log.info("Preloading " + supervisorRepository.save(new Supervisor("heinz@test.com", "Heinz", "Fiction", passwordEncoder.encode("12345678"), Role.SUPERVISOR,
                    "Als Projektmanager leite ich Projekte mit einem Fokus auf Effizienz und erfolgreiche Umsetzung. Meine Aufgaben umfassen die Planung, Koordination und Überwachung aller Projektphasen.",
                    "Product Manager, IT-Projektleiter", "", heinzInterests)));

            Set<Interest> arnoInterests = EnumSet.of(Interest.Datenbanken);
            log.info("Preloading " + supervisorRepository.save(new Supervisor("arno@test.com", "Arno", "Nym", passwordEncoder.encode("12345678"), Role.SUPERVISOR,
                    "Als Datenbankexperte navigiere ich durch komplexe Datenstrukturen, entwerfe effiziente Datenbankmodelle und gewährleiste eine reibungslose Datenverwaltung.","",
                    "", arnoInterests)));

            Set<Interest> justinInterests = EnumSet.of(Interest.Robotik, Interest.Virtual_Reality);
            log.info("Preloading " + supervisorRepository.save(new Supervisor("justin@test.com", "Justin", "Time", passwordEncoder.encode("12345678"), Role.SUPERVISOR,
                    "Als Experte für VR und Robotik vereine ich Innovation und Technologie, um immersive virtuelle Erlebnisse zu gestalten. Mit einem Fokus auf Roboterintegration arbeite ich an der Schaffung intelligenter, interaktiver Systeme.",
                    "Human-Machine Interaction Specialist", "Seit 6 Jahren betreue ich regelmäßig Studierende, insgesamt waren es schon 8.", justinInterests)));

            Set<Interest> anitaInterests = EnumSet.of(Interest.mobile_Anwendungen, Interest.Web_Development);
            log.info("Preloading " + supervisorRepository.save(new Supervisor("anita@test.com", "Anita", "Bath", passwordEncoder.encode("12345678"), Role.SUPERVISOR,
                    "Ich entwerfe kreative und benutzerfreundliche Webseiten oder Mobile Apps als Webentwicklerin. Mein Ziel ist es, durch innovatives Design und hochwertigen Code optimale Benutzererlebnisse zu schaffen.",
                    "Web- und App-Entwicklerin mit den gängingen Frameworks wie React, React Native und Flutter",
                    "", anitaInterests)));

            Set<Interest> claireInterests = EnumSet.of(Interest.Künstliche_Intelligenz);
            log.info("Preloading " + supervisorRepository.save(new Supervisor("claire@test.com", "Claire", "Grube", passwordEncoder.encode("12345678"), Role.SUPERVISOR,
                    "Als KI-Expertin erforsche und entwickle ich innovative Lösungen, die die Welt verändern. Mit Leidenschaft für maschinelles Lernen gestalte ich intelligente Systeme, um komplexe Herausforderungen zu bewältigen.",
                    "NLP-Engineering und KI Forscherin!", "Ich habe lange als Hilfswissenschaftlicherin gearbeitet und kenne mich mit Forschung und Schreiben etwas aus!", claireInterests)));

            Set<Interest> crystalInterests = EnumSet.of(Interest.Data_Analytics);
            log.info("Preloading " + supervisorRepository.save(new Supervisor("crystal@test.com", "Crystal", "Clearwater", passwordEncoder.encode("12345678"), Role.SUPERVISOR,
                    "In der Welt der Datenanalyse bin ich die Architektin von Erkenntnissen. Mit einer Leidenschaft für Daten forme ich Strategien, die Unternehmen helfen, aus Daten ihre besten Entscheidungen zu treffen.",
                    "Data Analyst / Data Scientist", "Durfte schon mal eine Bachelorarbeit betreuen!", crystalInterests)));

            Set<Interest> peterInterests = EnumSet.of(Interest.Computergrafik);
            log.info("Preloading " + supervisorRepository.save(new Supervisor("peter@test.com", "Peter", "Silie", passwordEncoder.encode("12345678"), Role.SUPERVISOR,
                    "Meine Fähigkeiten reichen von der Gestaltung von 2D- und 3D-Grafiken bis zur Animation, um fesselnde visuelle Erlebnisse zu schaffen.",
                    "3-D Modellierungsspezialist", "", peterInterests)));

            Set<Interest> kaiInterests = EnumSet.of(Interest.Systemadminstration, Interest.Netzwerke);
            log.info("Preloading " + supervisorRepository.save(new Supervisor("kai@test.com", "Kai", "Pirinha", passwordEncoder.encode("12345678"), Role.SUPERVISOR,
                    "Als Netzwerk- und Systemadministrator liegt meine Leidenschaft in der sicheren und effizienten Verwaltung von IT-Infrastrukturen. Die Aufrechterhaltung reibungsloser Betriebsabläufe sind mein tägliches Anliegen.",
                    "Sys-Admin", "", kaiInterests)));

            Supervisor notAvailableSupervisor1 = new Supervisor("rainer@test.com", "Rainer", "Zufall", passwordEncoder.encode("12345678"), Role.SUPERVISOR,
                    "In der Welt der IT fühle ich mich zuhause. Als leidenschaftlicher Technologie-Enthusiast liebe ich es, innovative Lösungen zu entwickeln und die digitale Welt durch kreative Anwendungen voranzutreiben.",
                    "HiWi", "Weiß wie man wissenschaftlich schreibt :)", Collections.emptySet());
            notAvailableSupervisor1.setAvailability(false);
            log.info("Preloading " + supervisorRepository.save(notAvailableSupervisor1));

            Supervisor notAvailableSupervisor2 = new Supervisor("klaus@test.com", "Klaus", "Trophobie", passwordEncoder.encode("12345678"), Role.SUPERVISOR,
                    "", "", "", Collections.emptySet());
            notAvailableSupervisor2.setAvailability(false);
            log.info("Preloading " + supervisorRepository.save(notAvailableSupervisor2));

        };
    }

}