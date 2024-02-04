package com.bamate.bamatebackend.auth.models;

import com.bamate.bamatebackend.account.models.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a {@code request} to register a new user account.
 * This class encapsulates the user's {@code firstName}, {@code lastName}, {@code email}, {@code password}, and {@code role}.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
}
