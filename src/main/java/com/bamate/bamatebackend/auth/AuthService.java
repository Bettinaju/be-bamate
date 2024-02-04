package com.bamate.bamatebackend.auth;

import com.bamate.bamatebackend.account.AccountNotFoundException;
import com.bamate.bamatebackend.account.AccountRepository;
import com.bamate.bamatebackend.account.models.Account;
import com.bamate.bamatebackend.account.models.Role;
import com.bamate.bamatebackend.auth.models.AuthenticationRequest;
import com.bamate.bamatebackend.auth.models.AuthenticationResponse;
import com.bamate.bamatebackend.auth.models.RegisterRequest;
import com.bamate.bamatebackend.config.JwtService;
import com.bamate.bamatebackend.supervisor.SupervisorRepository;
import com.bamate.bamatebackend.supervisor.models.Supervisor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service class responsible for authentication-related operations.
 * This service manages user registration and authentication processes.
 */
@Service
@RequiredArgsConstructor
public class AuthService {

    private final AccountRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;
    private final SupervisorRepository supervisorRepository;

    /**
     * Registers a new user based on the provided registration request.
     * @param request The registration request containing user details.
     * @return AuthenticationResponse containing the generated JWT token and user role.
     */
    public AuthenticationResponse register(RegisterRequest request) {
        var user = Account.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .role(request.getRole())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        if (request.getRole() == Role.SUPERVISOR) {
            Supervisor supervisor = new Supervisor(user);
           supervisorRepository.save(supervisor); // The supervisor has User data, and when it is saved, the User will also be saved.
        } else {
            Account savedUser = repository.save(user);
        }
        var jwtToken = jwtService.generateToken(user);
        Role userRole = user.getRole();
        return AuthenticationResponse.builder().token(jwtToken).role(userRole).email(user.getEmail()).build();
    }

    /**
     * Authenticates a user based on the provided authentication request.
     * @param request The authentication request containing user credentials.
     * @return AuthenticationResponse containing the generated JWT token and user role.
     * @throws AccountNotFoundException if the user account is not found.
     */
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authManager.authenticate( new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        var user = repository.findByEmail(
                request.getEmail()).orElseThrow(() -> new AccountNotFoundException(request.getEmail())
        );

        var jwtToken = jwtService.generateToken(user);
        Role userRole = user.getRole();

        return AuthenticationResponse.builder().token(jwtToken).role(userRole).email(user.getEmail()).build();
    }
}
