package com.bamate.bamatebackend.auth;

import com.bamate.bamatebackend.auth.models.AuthenticationRequest;
import com.bamate.bamatebackend.auth.models.AuthenticationResponse;
import com.bamate.bamatebackend.auth.models.RegisterRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller handling authentication-related endpoints.
 * This controller manages user registration and login processes.
 */
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    /**
     * Endpoint for user registration.
     * Registers a new user account based on the provided registration request.
     * @param request The registration request containing user details.
     * @return ResponseEntity containing the authentication response.
     */
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(service.register(request));
    }


    /**
     * Endpoint for user authentication.
     * Authenticates a user based on the provided authentication request.
     * @param request The authentication request containing user credentials.
     * @return ResponseEntity containing the authentication response.
     */
    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(service.authenticate(request));
    }

}
