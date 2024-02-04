package com.bamate.bamatebackend.config;

import com.bamate.bamatebackend.account.AccountNotFoundException;
import com.bamate.bamatebackend.account.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configuration class responsible for configuring application settings related to security and authentication.
 */
@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final AccountRepository repository;

    /**
     * Configures the {@code UserDetailsService} bean to load user details based on the provided {@code username}.
     * @return UserDetailsService implementation fetching user details from the {@code AccountRepository}.
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> repository
                .findByEmail(username)
                .orElseThrow(() -> new AccountNotFoundException(username));
    }

    /**
     * Configures the {@code AuthenticationProvider} bean responsible for authenticating users.
     * @return {@code AuthenticationProvider} implementation using the configured {@code userDetailsService} and {@code passwordEncoder}.
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    /**
     * Configures the {@code AuthenticationManager} bean responsible for managing user authentication.
     * @param config {@code AuthenticationConfiguration} object containing the authentication manager configuration.
     * @return {@code AuthenticationManager} instance obtained from the provided {@code AuthenticationConfiguration}.
     * @throws Exception if an error occurs while obtaining the {@code AuthenticationManager}.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /**
     * Configures the {@code PasswordEncoder} bean for encoding and decoding user passwords.
     * @return {@code PasswordEncoder} implementation using BCrypt hashing algorithm.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
