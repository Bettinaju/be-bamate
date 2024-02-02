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

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AccountRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authManager;
    private final SupervisorRepository supervisorRepository;


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
